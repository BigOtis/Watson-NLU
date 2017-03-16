package watson_developer_cloud.natural_language_understanding;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.ibm.watson.watson_developer_cloud.natural_language_understanding.NaturalLanguageUnderstanding;
import com.ibm.watson.watson_developer_cloud.natural_language_understanding.model.Analysis;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AnalysisTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AnalysisTest( String testName )
    {
        super( testName );
        try {
			System.getProperties().load(new FileInputStream("watson.properties"));
		} catch (IOException e) {
			System.err.println("MISSING MONGO.PROPERTIES FILE. DB WILL NOT LOAD CORRECTLY.");
		}
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AnalysisTest.class );
    }

    /**
     * Test String
     */
    public void testString()
    {
    	String username = System.getProperty("watson.analysis.username");
    	String password = System.getProperty("watson.analysis.password");
    	
    	NaturalLanguageUnderstanding nlu = new NaturalLanguageUnderstanding(username, password);
    	List<String> features =  Arrays.asList(new String[] { "concepts", "categories", "emotion", "entities", 
				  							"keywords", "relations", "semantic_roles", "sentiment" });
    	Analysis result = nlu.analyze(features, "IBM is an American multinational technology company headquartered in "
    			+ "Armonk, New York, United States, with operations in over 170 countries.", false).execute();
    	System.out.println(result.toString());
    }
    
    /**
     * Test URL
     */
    public void testURL()
    {
    	String username = System.getProperty("watson.analysis.username");
    	String password = System.getProperty("watson.analysis.password");
    	
    	NaturalLanguageUnderstanding nlu = new NaturalLanguageUnderstanding(username, password);
    	List<String> features =  Arrays.asList(new String[] { "concepts", "categories", "emotion", "entities", 
				  							"keywords", "relations", "semantic_roles", "sentiment" });
    	Analysis result = nlu.analyze(features, "www.example.com", true).execute();
    	System.out.println(result.toString());
    }
}
