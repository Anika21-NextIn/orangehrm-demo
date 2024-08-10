package testrunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoginTestRunner extends Setup {
    LoginPage loginPage;
    @Test(priority = 1, description = "Admin can't login without valid creds")
    public void doLoginWithWrongCreds(){
        loginPage=new LoginPage(driver);
        loginPage.doLogin("admin", "wrongpass");
        String textActual = driver.findElement(By.className("oxd-alert-content-text")).getText();
        String textExpected="Invalid credentials";
        Assert.assertTrue(textActual.contains(textExpected));
    }
    @Test(priority = 2, groups = "smoke", description = "Admin can login with valid creds")
    public void doLoginWithValidCreds() throws IOException, ParseException {
        loginPage=new LoginPage(driver);

        String fileLocation = "./src/test/resources/employees.json";
        JSONParser parser = new JSONParser();
        JSONArray empArray = (JSONArray) parser.parse(new FileReader(fileLocation));
        JSONObject adminCredObj= (JSONObject) empArray.get(0);

       // loginPage.doLogin("Admin", "admin123");
//        String adminUser =System.getProperty("userName");
//        String adminPass =System.getProperty("password");
       // JSONObject adminCredObj = (JSONObject) Utils.getUser().get(0);
//        String adminUser1=adminCredObj.get("userName").toString();
//        String adminPass1=adminCredObj.get("password").toString();

        if(System.getProperty("userName")!=null && System.getProperty("password")!=null){
            loginPage.doLogin(System.getProperty("userName"),System.getProperty("password"));
        }
        else{
            loginPage.doLogin(adminCredObj.get("userName").toString(), adminCredObj.get("password").toString());
        }
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
        boolean isImageExits = driver.findElement(By.className("oxd-userdropdown-img")).isDisplayed();
        Assert.assertTrue(isImageExits);
    }
    @Test(priority = 3)
    public void doLogout(){
        loginPage=new LoginPage(driver);
        loginPage.doLogout();
        String loginHeaderTitleActual = driver.findElement(By.className("orangehrm-login-title")).getText();
        String loginHeaderExpected = "Login";
        Assert.assertEquals(loginHeaderTitleActual, loginHeaderExpected);
    }

//    public static void main(String[] args) throws IOException, ParseException {
//        String fileLocation = "./src/test/resources/employees.json";
//        JSONParser parser = new JSONParser();
//        JSONArray empArray = (JSONArray) parser.parse(new FileReader(fileLocation));
//        JSONObject adminCredObj= (JSONObject) empArray.get(0);
//        System.out.println(adminCredObj.get("userName"));
//    }

}


