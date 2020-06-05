
package com.pattermatch.aws.lambda.java;

import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;

/**
 * The module containing all dependencies required by the {@link App}.
 */
public class DependencyFactory {

    private DependencyFactory() {}

    /**
     * @return an instance of LambdaClient
     */
    public static LambdaClient lambdaClient() {
        return LambdaClient.builder()
                       .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                       .region(Region.EU_WEST_2)
                       .httpClientBuilder(UrlConnectionHttpClient.builder())
                       .build();
    }
}
