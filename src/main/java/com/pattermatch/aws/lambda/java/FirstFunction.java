package com.pattermatch.aws.lambda.java;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvocationType;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;

/**
 * Lambda function entry point. You can change to use other pojo type or implement a different
 * RequestHandler.
 *
 * @see <a href=https://docs.aws.amazon.com/lambda/latest/dg/java-handler.html>Lambda Java
 *     Handler</a> for more information
 */
public class FirstFunction implements RequestHandler<Object, Object> {

  private static final String SECOND_FUNCTION_NAME = "SecondFunction";
  private final LambdaClient lambdaClient;

  public FirstFunction() {
    // Initialize the SDK client outside of the handler method so that it can be reused for subsequent invocations.
    // It is initialized when the class is loaded.
    lambdaClient = DependencyFactory.lambdaClient();
    // Consider invoking a simple api here to pre-warm up the application, eg: dynamodb#listTables
  }

  @Override
  public Object handleRequest(final Object input, final Context context) {
    SdkBytes payload = SdkBytes.fromUtf8String("{\"input\": \"test\"}");
    InvokeRequest invokeRequest = InvokeRequest.builder()
        .functionName(SECOND_FUNCTION_NAME)
        .invocationType(InvocationType.EVENT)
        .payload(payload)
        .build();
    InvokeResponse invokeResponse = lambdaClient.invoke(invokeRequest);
    System.out.println("Response: " + invokeResponse.toString());
    System.out.println("Response Payload: " + invokeResponse.payload().asUtf8String());
    return input;
  }
}
