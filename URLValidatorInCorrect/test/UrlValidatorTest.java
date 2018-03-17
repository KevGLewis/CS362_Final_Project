

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
