# import boto3
#
# s3 = boto3.client("s3")
#
# def handler(event, context):
#
#     print("Lambda triggered after S3 upload!")
#
#     # Extract bucket and key from event
#     record = event["Records"][0]
#     bucket_name = record["s3"]["bucket"]["name"]
#     object_key = record["s3"]["object"]["key"]
#
#     print(f"Bucket: {bucket_name}")
#     print(f"Key: {object_key}")
#
#     # Download file from S3
#     response = s3.get_object(Bucket=bucket_name, Key=object_key)
#     file_content = response["Body"].read().decode("utf-8")
#
#     print("File Contents:")
#     print(file_content)
#
#     # You can add processing logic here
#     # Example: check PDF, parse CSV, save metadata, etc.
#
#     return {"status": "Processed", "file": object_key}
def handler(event, context):
    print("Lambda invoked!")
    print("Event received:", event)

    # If invoked manually, no Records key
    if "Records" not in event:
        print("No S3 Records found. Manual invocation.")
        return {
            "status": "manual_test_ok",
            "msg": "Lambda executed without S3 trigger",
            "event": event
        }

    # S3 triggered invocation
    record = event["Records"][0]
    bucket = record["s3"]["bucket"]["name"]
    key = record["s3"]["object"]["key"]

    print(f"S3 Trigger Received → Bucket: {bucket}, Key: {key}")

    return {
        "status": "s3_trigger_ok",
        "bucket": bucket,
        "key": key
    }

