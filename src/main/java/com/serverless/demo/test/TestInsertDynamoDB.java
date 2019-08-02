package com.serverless.demo.test;

import java.util.HashMap;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;

public class TestInsertDynamoDB {
	
	public static void main(String[] args){
		System.out.println("============================START================================");
		insertItemToDynamoDBTable();
		System.out.println("============================END================================");
	}
	
	private static void insertItemToDynamoDBTable() {
		
		String table_name = "dynamodb-ajdel";
		String name = "121233447";
		
		
		HashMap<String,AttributeValue> item_values =
			    new HashMap<String,AttributeValue>();

			item_values.put("UserId", new AttributeValue(name));
			item_values.put("Name", new AttributeValue("John Doe3"));
			item_values.put("Position", new AttributeValue("Systems Analyst3"));

			final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.standard().withRegion("ap-southeast-1").build();

			try {
			    ddb.putItem(table_name, item_values);
			} catch (ResourceNotFoundException e) {
			    System.err.format("Error: The table \"%s\" can't be found.\n", table_name);
			    System.err.println("Be sure that it exists and that you've typed its name correctly!");
			    System.exit(1);
			} catch (AmazonServiceException e) {
			    System.err.println(e.getMessage());
			    System.exit(1);
			}
	}

}
