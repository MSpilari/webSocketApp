package dev.MSpilari.webSocketBackend.configs;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition;
import software.amazon.awssdk.services.dynamodb.model.BillingMode;
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest;
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement;
import software.amazon.awssdk.services.dynamodb.model.KeyType;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;

@Configuration
public class AwsServicesConfigs {

    private static final Logger logger = LoggerFactory.getLogger(AwsServicesConfigs.class);

    @Value("${spring.cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${spring.cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${spring.cloud.aws.region.static}")
    private String region;

    @Value("${spring.cloud.aws.s3.endpoint}")
    private String cloudEndpoint;

    @Value("${spring.cloud.aws.s3.bucket-name}")
    private String bucketName;

    @Value("${spring.cloud.aws.dynamodb.table-name}")
    private String tableName;

    @Bean
    public S3Client s3Client() {

        return S3Client.builder()
                .endpointOverride(URI.create(cloudEndpoint))
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                .serviceConfiguration(S3Configuration.builder()
                        .pathStyleAccessEnabled(true) // Força compatibilidade de caminho
                        .build())
                .build();
    }

    @Bean
    public DynamoDbClient dynamoDbClient() {
        return DynamoDbClient.builder()
                .endpointOverride(URI.create(cloudEndpoint))
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                .build();
    }

    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }

    @Bean
    public CommandLineRunner initializeAwsServices(S3Client s3Client, DynamoDbClient dynamoDbClient) {
        return args -> {
            createTableIfNotExists(dynamoDbClient);
            createBucketIfNotExists(s3Client);
        };
    }

    private void createBucketIfNotExists(S3Client s3Client) {
        logger.info("Trying to create a bucket on S3...");
        if (!doesBucketExist(s3Client)) {
            logger.info("Creating bucket with name: " + bucketName);
            s3Client.createBucket(CreateBucketRequest.builder().bucket(bucketName).build());
        } else {
            logger.info("Bucket " + bucketName + " already exists !");
        }
    }

    private boolean doesBucketExist(S3Client s3Client) {
        ListBucketsResponse listOfBuckets = s3Client.listBuckets();
        return listOfBuckets.buckets().stream().anyMatch(bucket -> bucket.name().equals(bucketName));
    }

    private void createTableIfNotExists(DynamoDbClient dynamoDbClient) {
        logger.info("Trying to create a table in DynamoDb...");
        if (!doesTableExist(dynamoDbClient)) {

            CreateTableRequest createTableRequest = CreateTableRequest.builder()
                    .tableName(tableName)
                    .keySchema(KeySchemaElement.builder()
                            .attributeName("album_id")
                            .keyType(KeyType.HASH)
                            .build())
                    .attributeDefinitions(AttributeDefinition.builder()
                            .attributeName("album_id")
                            .attributeType(ScalarAttributeType.S)
                            .build())
                    .billingMode(BillingMode.PAY_PER_REQUEST)
                    .build();
            logger.info("Creating a table with name: " + tableName + " in DynamoDb.");
            dynamoDbClient.createTable(createTableRequest);
        } else {
            logger.info("Table with name: " + tableName + " already exists.");
        }
    }

    private boolean doesTableExist(DynamoDbClient dynamoDbClient) {
        ListTablesResponse listOfTables = dynamoDbClient.listTables();

        return listOfTables.tableNames().stream().anyMatch(name -> name.equalsIgnoreCase(tableName));
    }
}
