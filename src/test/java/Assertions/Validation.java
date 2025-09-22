package Assertions;

import io.qameta.allure.Step;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;
import static Utils.Logs.log4j.info;

public class Validation {

    SoftAssert soft = new SoftAssert();

    @Step("validate assertEquals for actual result :{actual} by expected result :{expected}")
    public void assertEqualsString(String actual, String expected, String message)
    {
        soft.assertEquals(actual,expected,message);
        info("The assertion was Equal between Actual:(",actual,") and the expected:(",expected,")");
    }

    @Step("validate assertEquals for actual result :{actual} by expected result :{expected}")
    public void assertEqualsInt(int actual,int expected,String message)
    {
        soft.assertEquals(actual,expected,message);
        info("The assertion was Equal between Actual:( "+actual,") and the expected:( "+expected,").");
    }

    @Step("validate assertNotEquals for actual result :{actual} by expected result :{expected}")
    public void assertNotEquals(String actual,String expected,String message)
    {
        soft.assertNotEquals(actual,expected,message);
        info("The assertion was Not Equal between Actual:(",actual,") and the expected:(",expected,")");
    }

    @Step("validate assertTrue for actual result :{actual} to contain expected result :{expected}")
    public void assertTrueString(String actual, String expected, String message)
    {
        soft.assertTrue(actual.contains(expected),message);
        info("The assertion Was true as Actual:(",actual,") contained the expected:(",expected,")");
    }

    @Step("validate assertTrue for condition :{condition} to be true ")
    public void assertTrueBoolean(boolean condition,String message){
        soft.assertTrue(condition,message);
        info("The assertion Was true as condition:("+ condition,") is true");
    }

    @Step("validate assertFalse for actual result :{actual} to contain expected result :{expected}")
    public void assertFalse(String actual,String expected,String message)
    {
        soft.assertFalse(actual.contains(expected),message);
        info("The assertion Was false as Actual:(",actual,") didn't contain the expected:(",expected,")");
    }

    @Step("validate assertFail: {message}")
    public void assertFail(String message)
    {
        soft.fail(message);
        info("The assert failed as:",message);
    }

    @Step("validate assertAll")
    public void assertAll(){
        soft.assertAll();
        info("asserted All");
    }

    public void getClassAndMethodNames(ITestResult result){
        String className = result.getTestClass().getName();
        String methodName = result.getMethod().getMethodName();
      info("class name: ",className,", Method name: ", methodName,"Thread id:"+ Thread.currentThread().threadId());
    }
}

