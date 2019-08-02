package com.serverless.demo.service;

import java.util.HashMap;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Inserter implements RequestHandler<InserterInput, String> {

	@Override
	public String handleRequest(InserterInput input, Context context) {

		String table_name = "dynamodb-ajdel";

		HashMap<String, AttributeValue> item_values = new HashMap<String, AttributeValue>();

		item_values.put("UserId", new AttributeValue(input.getUserId()));
		item_values.put("Name", new AttributeValue(input.getName()));
		item_values.put("Position", new AttributeValue(input.getPosition()));

		final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.standard().withRegion("ap-southeast-1").build();

		try {
			ddb.putItem(table_name, item_values);
		} catch (ResourceNotFoundException e) {
			System.err.format("Error: The table \"%s\" can't be found.\n", table_name);
			System.err.println("Be sure that it exists and that you've typed its name correctly!");
		} catch (AmazonServiceException e) {
			System.err.println(e.getMessage());
		}

		return "Success Insert!!!";
	}

}
