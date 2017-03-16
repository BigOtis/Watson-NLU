# Watson-NLU
Java API for the Watson Natural Language Understanding Service

This project extends the existing Watson Java-SDK to add support for the Watson NLU service.

It simply is a Java wrapper for the API's cURL calls described here:
https://www.ibm.com/watson/developercloud/natural-language-understanding/api/v1/#post-analyze

The request to the API returns a class named "Analysis" which provides the JSON objects returned by the service. 

Code usage example:

```java
String username = System.getProperty("watson.analysis.username");
String password = System.getProperty("watson.analysis.password");

NaturalLanguageUnderstanding nlu = new NaturalLanguageUnderstanding(username, password);
List<String> features =  Arrays.asList(new String[] { 	
	"concepts", "categories", "emotion", "entities", 
	"keywords", "relations", "semantic_roles", "sentiment" });

Analysis result = nlu.analyze(features, "www.example.com", true).execute();
System.out.println(result.toString());
```

Keyword Result Example:
```json
"keywords": [
    {
      "text": "prior coordination",
      "relevance": 0.991001
    },
    {
      "text": "illustrative examples",
      "relevance": 0.942393
    },
    {
      "text": "permission",
      "relevance": 0.825913
    },
    {
      "text": "domain",
      "relevance": 0.824806
    },
    {
      "text": "documents",
      "relevance": 0.731819
    },
    {
      "text": "information",
      "relevance": 0.723005
    }
  ],
```
