package com.ibm.watson.watson_developer_cloud.natural_language_understanding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonObject;
import com.ibm.watson.developer_cloud.http.RequestBuilder;
import com.ibm.watson.developer_cloud.http.ServiceCall;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.Classification;
import com.ibm.watson.developer_cloud.service.WatsonService;
import com.ibm.watson.developer_cloud.util.ResponseConverterUtils;
import com.ibm.watson.developer_cloud.util.Validator;
import com.ibm.watson.watson_developer_cloud.natural_language_understanding.model.Analysis;

import okhttp3.Request;

/**
 * NaturalLangaugeUnderstanding
 * 
 * Phillip Lopez - pgl5711@rit.edu
 */
public class NaturalLanguageUnderstanding extends WatsonService {

  private static final String SERVICE_NAME = "natural_language_understanding";
  private static final String TEXT_PARAM = "text";
  private static final String URL_PARAM = "url";
  private static final String PATH_ANALYZE = "/v1/analyze?version=2017-02-27";
  private static final String URL = "https://gateway.watsonplatform.net/natural-language-understanding/api";
  private static final String METADATA = "metadata";
  private static final List<String> FEATURES = Arrays.asList(new String[] { "concepts", "categories", "emotion", "entities", 
		  												  "keywords", "relations", "semantic_roles", 
		  												  "sentiment" });

  /**
   * Instantiates a new Natural Language Classifier service.
   */
  public NaturalLanguageUnderstanding() {
    super(SERVICE_NAME);
    if ((getEndPoint() == null) || getEndPoint().isEmpty()) {
      setEndPoint(URL);
    }
  }

  /**
   * Instantiates a new natural language understanding service by username and password.
   *
   * @param username the username
   * @param password the password
   */
  public NaturalLanguageUnderstanding(String username, String password) {
	  this();
	  setUsernameAndPassword(username, password);
  }

  /**
   * Returns classification information for a classifier on a phrase.
   *
   * @param classifierId The classifier id
   * @param textOrURL The submitted phrase to analyze or URL
   * @return the classification of a phrase with a given classifier
   */
  public ServiceCall<Analysis> analyze(List<String> features, final String textOrURL, boolean isURL) {
	  Validator.isTrue((features != null && features.size() > 0), "you must select a least one feature to analyze");
	  for(String feature : features){
		  Validator.isTrue((getFeatures().contains(feature)), features + " is not a valid NLU feature");
	  }
	  Validator.isTrue((textOrURL != null) && !textOrURL.isEmpty(), "text cannot be null or empty");
	  
	  final JsonObject contentJson = new JsonObject();
	  if(isURL){
		  contentJson.addProperty(URL_PARAM, textOrURL);
		  features = new ArrayList<>(features);
		  features.add(METADATA);
	  }
	  else{
		  contentJson.addProperty(TEXT_PARAM, textOrURL);
	  }
	  
	  JsonObject featuresJson = new JsonObject();
	  for(String feature : features){
		  featuresJson.add(feature, new JsonObject());
	  }
	  contentJson.add("features", featuresJson);	  
	  
      final Request request = RequestBuilder.post(PATH_ANALYZE).bodyJson(contentJson).build();
      return createServiceCall(request, ResponseConverterUtils.getObject(Analysis.class));
  }

  /**
   * Returns the Natural Language Understanding features that 
   * can be analyzed by the Watson NLU service
   * 
   * @return List<String> of Features
   */
  public List<String> getFeatures(){
	  return FEATURES;
  }
  
}