package testrunner;

import config.Setup;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.Utils;

import java.io.IOException;

public class UserTestRunner extends Setup {
    LoginPage loginPage;
    @Test(priority = 2, description = "New user can login successfully by valid creds")
    public void doLoginByUser() throws IOException, ParseException {
        loginPage=new LoginPage(driver);
       String userName =  Utils.getUser().get("userName").toString();
        String password =  Utils.getUser().get("password").toString();
        loginPage.doLogin(userName, password);
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
        boolean isImageExits = driver.findElement(By.className("oxd-userdropdown-img")).isDisplayed();
        Assert.assertTrue(isImageExits);
    }
}
