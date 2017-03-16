package com.ibm.watson.watson_developer_cloud.natural_language_understanding.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.ibm.watson.developer_cloud.service.model.GenericModel;

/**
 * Natural Language Analysis
 * 
 * @author Phillip Lopez - pgl5711@rit.edu
 *
 */
public class Analysis extends GenericModel{

	@SerializedName("concepts") 		private JsonArray concepts;
	@SerializedName("categories") 		private JsonArray categories;
	@SerializedName("emotion") 			private JsonObject emotion;
	@SerializedName("entities") 		private JsonArray entities;
	@SerializedName("keywords") 		private JsonArray keywords;
	@SerializedName("metadata") 		private JsonObject metadata;
	@SerializedName("relations") 		private JsonArray relations;
	@SerializedName("semantic_roles") 	private JsonArray semantic_roles;
	@SerializedName("sentiment") 		private JsonObject sentiment;

	
}
