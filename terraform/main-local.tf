terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
}

# --------------------------
# LocalStack Provider
# --------------------------
provider "aws" {
  access_key                  = "test"
  secret_key                  = "test"
  region                      = "ap-south-1"
  s3_use_path_style           = true

  skip_credentials_validation = true
  skip_metadata_api_check     = true
  skip_requesting_account_id  = true
  skip_region_validation      = true

  #mv main-aws.tf main-aws.tf.disabled
  # IMPORTANT: Point AWS to LocalStack instead of AWS Cloud
  #mv main-local.tf main-local.tf.disabled

  endpoints {
    s3 = "http://localhost:4566"
    sts = "http://localhost:4566"   # ← MANDATORY LINE YOU MISSED ✔ STS = AWS Security Token Service
    ecr = "http://localhost:4566"
    iam = "http://localhost:4566"
    lambda = "http://localhost:4566"
    #ec2 = "http://localhost:4566"
  }
}

# --------------------------
# Create ECR Repository
# --------------------------
# resource "aws_ecr_repository" "report_repo" {
#   name = "report-service"
# }

# --------------------------
# Create S3 bucket
# --------------------------
resource "aws_s3_bucket" "my_bucket" {
  #bucket = "terraform-demo-bucket"
  bucket = "springboot-terraform-bucket"
}

############################################
# IAM ROLE (Dummy for LocalStack)
############################################
resource "aws_iam_role" "lambda_exec" {
  name = "lambda_execution_role"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [{
      Action    = "sts:AssumeRole"
      Effect    = "Allow"
      Principal = { Service = "lambda.amazonaws.com" }
    }]
  })
}

############################################
# AUTO ZIP LAMBDA CODE
############################################
data "archive_file" "lambda_zip" {
  type        = "zip"
  source_file = "${path.module}/lambda/lambda_function.py"
  output_path = "${path.module}/lambda/lambda.zip"
}

############################################
# LAMBDA FUNCTION (uses auto zip)
############################################
resource "aws_lambda_function" "process_report" {
  function_name = "processReport"

  runtime = "python3.11"
  handler = "lambda_function.handler"
  role    = aws_iam_role.lambda_exec.arn

  filename         = data.archive_file.lambda_zip.output_path
  source_code_hash = data.archive_file.lambda_zip.output_base64sha256
}

# 4. Lambda permission (Required for S3 → Lambda)
resource "aws_lambda_permission" "allow_s3" {
  statement_id  = "AllowS3Invoke"
  action        = "lambda:InvokeFunction"
  function_name = aws_lambda_function.process_report.function_name
  principal     = "s3.amazonaws.com"
}

######
resource "aws_s3_bucket_notification" "bucket_notifications" {
  bucket = aws_s3_bucket.my_bucket.id

  lambda_function {
    lambda_function_arn = aws_lambda_function.process_report.arn
    events              = ["s3:ObjectCreated:*"]
  }

  depends_on = [
    aws_lambda_function.process_report,
    aws_lambda_permission.allow_s3
  ]
}

### EC2
#1. Create Security Group
# resource "aws_security_group" "ec2_sg" {
#   name        = "local-ec2-sg"
#   description = "Fake EC2 SG"
# }

# 2. Create EC2 instance
# resource "aws_instance" "test_ec2" {
#   ami           = "ami-12345678"   # LocalStack accepts ANY AMI ID
#   instance_type = "t2.micro"
#   security_groups = [aws_security_group.ec2_sg.name]
#
#   tags = {
#     Name = "localstack-test-ec2"
#   }
# }
