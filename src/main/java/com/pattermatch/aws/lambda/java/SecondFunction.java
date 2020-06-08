package com.pattermatch.aws.lambda.java;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Lambda function entry point. You can change to use other pojo type or implement a different
 * RequestHandler.
 *
 * @see <a href=https://docs.aws.amazon.com/lambda/latest/dg/java-handler.html>Lambda Java
 *     Handler</a> for more information
 */
public class SecondFunction implements RequestHandler<Object, Object> {

  @Override
  public Object handleRequest(final Object input, final Context context) {
    System.out.println("Got input: " + input);
//    throw new RuntimeException("Something bad happened");
    return input;
  }
}
