

import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
      super(testName);
   }

   public void customAssert(String statement, boolean boolOne, boolean boolTwo)
   {
       String assertionError = null;
       try {
           assertEquals(boolOne,boolTwo);
       }
       catch (AssertionError ae) {
           assertionError = ae.toString();
       }
       
       if(assertionError != null)
    	   		System.out.println(statement + " " + assertionError);
   }
   
   
   public void testManualTest()
   {
//You can use this function to implement your manual testing	  
	   // Set up the validator to accept all schemes
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES );
	   
	   // Insert the manual URls that we are going to test
	   String[] trueURLS = {"http://www.google.com", // Typical 
	                       "http://www.google.com/", // Typical with slash
	                       "http://0.0.0.0/", // local address
	                       "http://www.google.com/test", // With a 
	                       "http://stackoverflow.com/", // No Domain
	                       "www.stackoverflow.com/test/testTwo/test/test/test/test/test/test/testFile" // A long domain
	                       };
	   
	   // List all of the URLs we are going to manually test
	   String[] falseURLS = {"http://www.google.cm/", // Bad TLD
			   "http://www.google.cmo/", // verify bad TLD
			   "www.google.jdifj", // Checking different bad TLD cases
			   "www.google.jdifj/",
			   "http://www.google.jdifj/",
			   "htp:/www.google.com/", // Incorrect Scheme Variations
			   "http:/www.google.com/",
			   "http:www.google.com/",
			   "www.google.com/#/", //Path Options
			   "www.google.com/23/",
			   "255.255.255.255" // No Scheme
			   
			   };
	   
	   // Run through the true URLs
	   for(int i = 0; i < trueURLS.length; i++) 
	   {
		   customAssert(trueURLS[i], true, urlVal.isValid(trueURLS[i]));
	   }
	   
	   // Run through the false URLs
	   for(int i = 0; i < falseURLS.length; i++) 
	   {
		   customAssert(falseURLS[i], false, urlVal.isValid(falseURLS[i]));
	   }
	  
   }
   
   
   public void testYourFirstPartition()
   {
	 //You can use this function to implement your First Partition testing	   

   }
   
   public void testYourSecondPartition(){
		 //You can use this function to implement your Second Partition testing	   

   }
   //You need to create more test cases for your Partitions if you need to 
   
   public void testIsValid()
   {
	   //You can use this function for programming based testing

   }
   
   public static void main(String[] argv) {

	   UrlValidatorTest fct = new UrlValidatorTest("url test");
      fct.testManualTest();
   }

 //-------------------- Test data for creating a composite URL
   /**
    * The data given below approximates the 4 parts of a URL
    * <scheme>://<authority><path>?<query> except that the port number
    * is broken out of authority to increase the number of permutations.
    * A complete URL is composed of a scheme+authority+port+path+query,
    * all of which must be individually valid for the entire URL to be considered
    * valid.
    */
   ResultPair[] testUrlScheme = {new ResultPair("http://", true),
                               new ResultPair("ftp://", true),
                               new ResultPair("h3t://", true),
                               new ResultPair("3ht://", false),
                               new ResultPair("http:/", false),
                               new ResultPair("http:", false),
                               new ResultPair("http/", false),
                               new ResultPair("://", false),
                               new ResultPair("", true)};

   ResultPair[] testUrlAuthority = {new ResultPair("www.google.com", true),
                                  new ResultPair("go.com", true),
                                  new ResultPair("go.au", true),
                                  new ResultPair("0.0.0.0", true),
                                  new ResultPair("255.255.255.255", true),
                                  new ResultPair("256.256.256.256", false),
                                  new ResultPair("255.com", true),
                                  new ResultPair("1.2.3.4.5", false),
                                  new ResultPair("1.2.3.4.", false),
                                  new ResultPair("1.2.3", false),
                                  new ResultPair(".1.2.3.4", false),
                                  new ResultPair("go.a", false),
                                 new ResultPair("go.a1a", false),
                                  new ResultPair("go.1aa", false),
                                  new ResultPair("aaa.", false),
                                  new ResultPair(".aaa", false),
                                  new ResultPair("aaa", false),
                                  new ResultPair("", false)
   };
   ResultPair[] testUrlPort = {new ResultPair(":80", true),
                             new ResultPair(":65535", true),
                             new ResultPair(":0", true),
                             new ResultPair("", true),
                             new ResultPair(":-1", false),
                            new ResultPair(":65636",false),
                             new ResultPair(":65a", false)
   };
   ResultPair[] testPath = {new ResultPair("/test1", true),
                          new ResultPair("/t123", true),
                          new ResultPair("/$23", true),
                          new ResultPair("/..", false),
                          new ResultPair("/../", false),
                          new ResultPair("/test1/", true),
                          new ResultPair("", true),
                          new ResultPair("/test1/file", true),
                          new ResultPair("/..//file", false),
                          new ResultPair("/test1//file", false)
   };
   //Test allow2slash, noFragment
   ResultPair[] testUrlPathOptions = {new ResultPair("/test1", true),
                                    new ResultPair("/t123", true),
                                    new ResultPair("/$23", true),
                                    new ResultPair("/..", false),
                                    new ResultPair("/../", false),
                                    new ResultPair("/test1/", true),
                                    new ResultPair("/#", false),
                                    new ResultPair("", true),
                                    new ResultPair("/test1/file", true),
                                    new ResultPair("/t123/file", true),
                                    new ResultPair("/$23/file", true),
                                    new ResultPair("/../file", false),
                                    new ResultPair("/..//file", false),
                                    new ResultPair("/test1//file", true),
                                    new ResultPair("/#/file", false)
   };

   ResultPair[] testUrlQuery = {new ResultPair("?action=view", true),
                              new ResultPair("?action=edit&mode=up", true),
                              new ResultPair("", true)
   };

}
