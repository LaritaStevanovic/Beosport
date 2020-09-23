import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BeosportLoginForm {

    public static final WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, 60);

    //Test envirment

    String testEnv = "https://rs.beosport.com/customer/account/login/";

    //Locators

    String EmailField = "email";
    String PassField = "pass";
    String LoginButton = "send2";
    String Validlogin = "page-title-wrapper";

    // TestData

    String ValidEmail = "stevanovic.larita@gmail.com";
    String ValidPass = "Cvarkovdjordje1";

    String WrongEmail = "pedja1984";
    String WrongPassLessch = "zik";

    String Wrongpass = "testtest";
    String WrongEmail2 = "kikiriki@gmail.com";

    //Error mesages

    String emailErrorMsg = "advice-required-entry-email";
    String passErrorMsg = "advice-required-entry-pass";

    String erormsgWrongPass = "li.error-msg";

    String lesschPassErorrMsg = "advice-validate-password-pass";

    // expected error mesages

    String ExEmailMessage = "Ovo polje je obavezno.";
    String ExPassMessage = "Ovo polje je obavezno.";
    String pass2ExpectedMsg = "Nevažeća prijava ili lozinka.";

    String lesschPassErorMsg = "Molimo unesite 6 ili više karaktera bez praznih polja na početku i kraju.";
    //

    @Before
    public void openLoginForm() {

        // open log form

        driver.get(testEnv);
        driver.manage().window().maximize();
        System.out.println("01. open log form");

    }


    // 01. Submit all required fields empty
    @Test
    public void test01() throws Exception{

        wait.until(ExpectedConditions.elementToBeClickable(By.id(LoginButton)));
        driver.findElement(By.id(LoginButton)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(emailErrorMsg)));

        if(driver.findElement(By.id(emailErrorMsg)) != null) {
            String actualEmailErorMessage = driver.findElement(By.id(emailErrorMsg)).getText();
            Assert.assertEquals(ExEmailMessage, actualEmailErorMessage);
        }
        else {
            throw new Exception("error message  is not present");
        }
    }

    //02. Submit valid data in email field and leave empty password field
    @Test
    public void test02() throws Exception{

        wait.until(ExpectedConditions.elementToBeClickable(By.id(LoginButton)));
        driver.findElement(By.id(EmailField)).sendKeys(ValidEmail);
        driver.findElement(By.id(LoginButton)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(passErrorMsg)));

        if(driver.findElement(By.id(passErrorMsg)) != null) {
            String actualPassErorMessage = driver.findElement(By.id(passErrorMsg)).getText();
            Assert.assertEquals(ExPassMessage, actualPassErorMessage);
        }
        else {
            throw new Exception("error message for pass  is not present");
        }
    }

    //03. Submit empty email field and valid data in password field
    @Test
    public void test03() throws Exception{

        wait.until(ExpectedConditions.elementToBeClickable(By.id(LoginButton)));
        driver.findElement(By.id(PassField)).sendKeys(ValidPass);
        driver.findElement(By.id(LoginButton)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(emailErrorMsg)));

        if(driver.findElement(By.id(emailErrorMsg)) != null) {
            String actualEmailErorMessage = driver.findElement(By.id(emailErrorMsg)).getText();
            Assert.assertEquals(ExEmailMessage, actualEmailErorMessage);
        }
        else {
            throw new Exception("error message for email is not present");
        }

    }

    //04. Submit valid data in email field and wrong data in password field
    @Test
    public void test04() throws Exception{

        wait.until(ExpectedConditions.elementToBeClickable(By.id(LoginButton)));
        driver.findElement(By.id(EmailField)).sendKeys(ValidEmail);
        driver.findElement(By.id(PassField)).sendKeys(Wrongpass);
        driver.findElement(By.id(LoginButton)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(erormsgWrongPass)));

        if(driver.findElement(By.cssSelector(erormsgWrongPass)) != null) {
            String actualpass2ErorMessage = driver.findElement(By.cssSelector(erormsgWrongPass)).getText();
            Assert.assertEquals(pass2ExpectedMsg, actualpass2ErorMessage);
        }
        else {
            throw new Exception("error message for pass is not present");
        }


    }

    //05. Submit wrong data in email field and valid data in password field
    @Test
    public void test05() throws Exception{

        wait.until(ExpectedConditions.elementToBeClickable(By.id(LoginButton)));
        driver.findElement(By.id(EmailField)).sendKeys(WrongEmail2);
        driver.findElement(By.id(PassField)).sendKeys(ValidPass);
        driver.findElement(By.id(LoginButton)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(erormsgWrongPass)));

        if(driver.findElement(By.cssSelector(erormsgWrongPass)) != null) {
            String actualpass2ErorMessage = driver.findElement(By.cssSelector(erormsgWrongPass)).getText();
            Assert.assertEquals(pass2ExpectedMsg, actualpass2ErorMessage);
        }
        else {
            throw new Exception("error message for email is not present");
        }

    }

    //06. Submit valid data in email field and less than 6 characters in password field
    @Test
    public void test06() throws Exception{

        wait.until(ExpectedConditions.elementToBeClickable(By.id(LoginButton)));
        driver.findElement(By.id(EmailField)).sendKeys(ValidEmail);
        driver.findElement(By.id(PassField)).sendKeys(WrongPassLessch);
        driver.findElement(By.id(LoginButton)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(lesschPassErorrMsg)));

        if(driver.findElement(By.id(lesschPassErorrMsg)) != null) {
            String actualpassLesschErorMessage = driver.findElement(By.id(lesschPassErorrMsg)).getText();
            Assert.assertEquals(lesschPassErorMsg, actualpassLesschErorMessage);
        }
        else {
            throw new Exception("error message for pass is not present");
        }

    }

    //07.Submit valid data in all required fields
    @Test
    public void test07() throws Exception{

        wait.until(ExpectedConditions.elementToBeClickable(By.id(LoginButton)));
        driver.findElement(By.id(EmailField)).sendKeys(ValidEmail);
        driver.findElement(By.id(PassField)).sendKeys(ValidPass);
        driver.findElement(By.id(LoginButton)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(Validlogin)));

        if(driver.findElement(By.cssSelector(Validlogin)) != null) {
            String actualValidlogin = driver.findElement(By.cssSelector(Validlogin)).getText();
            Assert.assertEquals(Validlogin, actualValidlogin);
        }
        else {
            throw new Exception("Valid login element is not present");
        }
    }
}
