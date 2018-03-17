

import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest()
   {
//You can use this function to implement your manual testing	  
	   // Set up the validator to accept all schemes
	   UrlValidator urlVal = new UrlValidator();
	   
	  customAssert("http://www.google.aaab", false, urlVal.isValid("http://www.google.aaab"));
	   
	  //Test the custom scheme functionality
	  String[] schemes = {"testone","xxxyyy"};
	  UrlValidator urlValCustScheme = new UrlValidator(schemes);
	  
	  customAssert("testone://www.google.com", true,urlValCustScheme.isValid("testone://www.google.com"));
	  customAssert("xxxyyy://www.google.com/", true,urlValCustScheme.isValid("xxxyyy://www.google.com/"));
	  customAssert("http://www.google.com/", false,urlValCustScheme.isValid("http://www.google.com/"));
	   
	   // Set up a validator that also accepts two slashes and the Local URLs, testing these options
	   UrlValidator urlValTwoSlash = new UrlValidator(null, null, UrlValidator.ALLOW_2_SLASHES + UrlValidator.ALLOW_LOCAL_URLS);
	   
	   customAssert("ftp://www.google.com/test//testfile", true,urlValTwoSlash.isValid("ftp://www.google.com/test//testfile"));
	  
	   String[] trueURLsTwoSlash = { // Typical 
               "http://localhost/", // Typical with slash
               "http://machine/", // local address
               "http://localhost:8000", // with ports
               "ftp://localhost:8000",
               "http://machine:0",
               "http://www.google.com/test//testfile",
               "ftp://www.google.com/test//testfile",
               "ftp://www.google.com/file1//test2//test3"
               };
	   
	   // Insert the manual URls that we are going to test
	   String[] trueURLS = {"http://www.google.com", // Typical 
	                       "http://www.google.com/", // Typical with slash
	                       "http://0.0.0.0/", // local address
	                       "http://www.google.com/test", // With a 
	                       "http://stackoverflow.com/", // No Domain
	                       "http://www.google.com/test/test/test/test/test/test/testFile", // A long domain with scheme
	                       "http://www.google.com/test/test/test/test/test/test/testFile", // A long domain with scheme
	                       "http://www.google.com/test?action=view",
	                       "http://www.google.com/#/",// Path option with symbol
	        			   	   "http://www.google.com/23/",
	                       "http://www.google.ceo", // Several TLD
	                       "http://www.google.food",
	                       "http://www.google.com:0", // Good Ports
	                       "http://www.google.com:6000"
	                       };
	   
	   // List all of the URLs we are going to manually test
	   String[] falseURLS = {"http://www.google.cmx/", // Bad TLD
			   "http://www.google.aaab", // verify bad TLD
			   "http://www.google.jdifj", // Checking different bad TLD cases
			   "http://www.google.jdifj/",
			   "http://www.google.jdifj/",
			   "ftp://www.google.jdifj", // Checking different bad TLD cases
			   "ftp://www.google.jdifj/",
			   "ftp://www.google.jdifj/",
			   "htp:/www.google.com/", // Incorrect Scheme Variations
			   "http:/www.google.com/",
			   "http:/www.google.com/#", //address with incorrect path option",
			   "255.255.255.255", // No Scheme
			   "http://256.256.256.256", // Impossible IP address
			   "ftp://256.256.256.256",
			   "http://www.google.com/..//file", // Testing dots in the file
			   "http://www.google.com:-1", // Bad Ports
			   "http://www.google.com:65a", 
			   "http://www.google.com/test//testfile", // Try two Slashes
               "http://localhost/", // Typical with slash
               "http://machine/", // local address
               "http://localhost:8000", // with ports
               "ftp://localhost:8000",
               "http://machine:0",
               "http://www.google.com/test//testfile",
               "ftp://www.google.com/test//testfile",
               "ftp://www.google.com/file1//test2//test3"
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
	   
	   // Run through the true URLs with the two slash option
	   for(int i = 0; i < trueURLsTwoSlash.length; i++) 
	   {
		   customAssert(trueURLsTwoSlash[i], true, urlValTwoSlash.isValid(trueURLsTwoSlash[i]));
	   }
   }
   
   
   public void testYourFirstPartition()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   
	   // PARTITION (1): URL COMPONENTS IN CORRECT ORDER
	   //		a) all components valid
	   assertTrue(urlVal.isValid("http://www.google.com:80/test?action=view"));
	   
	   //		b) all components invalid
	   assertFalse(urlVal.isValid("htp:///w.google.asjdckl:999999999//test??action=view"));
	   
	   //		c) some components valid some invalid
	   assertFalse(urlVal.isValid("htttp://wwww.google.com:80//test?action=view"));
	   
	   //		d) some missing required components but rest are valid
	   assertFalse(urlVal.isValid("http://google/test?action=view"));
	   
	   //		e) some missing components and rest are invalid
	   assertFalse(urlVal.isValid("htttp:///google.com//test??action==view"));
	   
	   //		f) optional components missing but rest valid
	   assertTrue(urlVal.isValid("http://www.google.com"));
	   
	   //		g) optional components missing and rest invalid
	   assertFalse(urlVal.isValid("w.google.caosjdfioa;"));	   
	   
	   //		h) special case: 'file' allows empty authority
	   assertTrue(urlVal.isValid("file://myfile?action=view"));
   }
   
   
   public void testYourSecondPartition(){
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   
	   // PARTITION (2): URL COMPONENTS TESTED ARE IN THE INCORRECT ORDER
	   //		a) all components valid
	   assertFalse(urlVal.isValid(":80www.http://google.com/test?action=view"));
	   
	   //		b) all components invalid
	   assertFalse(urlVal.isValid("w.google.htp:///asjdckl//test??action=view;:999999999"));
	   
	   //		c) some components valid some invalid
	   assertFalse(urlVal.isValid(":80//test?action=view.htttp://wwww.google.com"));
	   
	   //		d) some missing required components but rest are valid
	   assertFalse(urlVal.isValid("/test?action=viewhttp://google/"));
	   
	   //		e) some missing required components and rest are invalid
	   assertFalse(urlVal.isValid("google//test??action==viewhtttp:///"));
	   
	   //		f) optional components missing but rest valid
	   assertFalse(urlVal.isValid("com.www.googlehttp://"));
	   
	   //		g) optional components missing and rest invalid
	   assertFalse(urlVal.isValid("caosjdfioa;.google.ww"));	   
	   
	   //		h) special case: 'file' allows empty authority
	   assertFalse(urlVal.isValid("myfile?file://action=view"));
   }

   
   public void testIsValid()
   {
	   //You can use this function for programming based testing

   }
   


}
