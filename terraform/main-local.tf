terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
}

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
  }
}

resource "aws_s3_bucket" "my_bucket" {
  bucket = "terraform-demo-bucket"
}
