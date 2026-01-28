package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

public class Main {

    public static void main(String[] args) {

        System.setProperty("webdriver.edge.driver", "D:/EdgeWebDriver/msedgedriver.exe/");

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");

        WebDriver driver = new EdgeDriver();

        try {
            driver.get("https://user:senlatest@regoffice.senla.eu/");

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

            WebElement buttonUserByText = driver.findElement(By.xpath("//div/button[text()=\"Войти как пользователь\"]"));
            buttonUserByText.click();

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

            WebElement searchSurnameField = driver.findElement(By.id("TextInputField-1"));
            searchSurnameField.sendKeys("Козека", Keys.ENTER);

            WebElement searchNameField = driver.findElement(By.id("TextInputField-2"));
            searchNameField.sendKeys("Александр", Keys.ENTER);

            WebElement search2ndNameField = driver.findElement(By.id("TextInputField-3"));
            search2ndNameField.sendKeys("Юрьевич", Keys.ENTER);

            WebElement searchTelephoneNumberField = driver.findElement(By.id("TextInputField-4"));
            searchTelephoneNumberField.sendKeys("77777777", Keys.ENTER);

            WebElement searchPassportNumberField = driver.findElement(By.id("TextInputField-5"));
            searchPassportNumberField.sendKeys("АВ1111111", Keys.ENTER);

            WebElement searchRegistrationAddressField = driver.findElement(By.id("TextInputField-6"));
            searchRegistrationAddressField.sendKeys("г. Брест", Keys.ENTER);

            Thread.sleep(2000);

            WebElement buttonNextByText = driver.findElement(By.xpath("//*[text()=\"Далее\"]"));
            buttonNextByText.click();

            Thread.sleep(2000);

            WebElement buttonRegistrationOfMarriage = driver.findElement(By.xpath("//button[text()=\"Регистрация брака\"]"));
            buttonRegistrationOfMarriage.click();

            Thread.sleep(2000);

            WebElement searchMRSurnameField = driver.findElement(By.id("TextInputField-7"));
            searchMRSurnameField.sendKeys("Вашкевич", Keys.ENTER);

            WebElement searchMRNameField = driver.findElement(By.id("TextInputField-8"));
            searchMRNameField.sendKeys("Екатерина", Keys.ENTER);

            WebElement searchMR2ndNameField = driver.findElement(By.id("TextInputField-9"));
            searchMR2ndNameField.sendKeys("Вячеславовна", Keys.ENTER);

            WebElement searchMRDateOfBirth = driver.findElement(By.id("TextInputField-10"));
            searchMRDateOfBirth.sendKeys("27012004", Keys.ENTER);

            WebElement searchMRPassportNumberField = driver.findElement(By.id("TextInputField-11"));
            searchMRPassportNumberField.sendKeys("АВ2222222", Keys.ENTER);

            WebElement searchMRSex = driver.findElement(By.id("TextInputField-12"));
            searchMRSex.sendKeys("female", Keys.ENTER);

            WebElement searchMRRegistrationAddressField = driver.findElement(By.id("TextInputField-13"));
            searchMRRegistrationAddressField.sendKeys("г. Брест", Keys.ENTER);

            Thread.sleep(2000);

            WebElement buttonMRNextByText = driver.findElement(By.xpath("//*[text()=\"Далее\"]"));
            buttonMRNextByText.click();

            Thread.sleep(2000);

            WebElement searchMRDataDateOfRegistration = driver.findElement(By.xpath("//input[@id=(//label[contains(., 'Дата регистрации')]/@for)]"));
            searchMRDataDateOfRegistration.sendKeys("20072026", Keys.ENTER);

            WebElement searchMRNewSurnameField = driver.findElement(By.xpath("//input[@id=(//label[contains(., 'Новая фамилия')]/@for)]"));
            searchMRNewSurnameField.sendKeys("Козека", Keys.ENTER);

            WebElement searchMRSpouseSurnameField = driver.findElement(By.xpath("//input[@id=(//label[contains(., 'Фамилия супруга/и')]/@for)]"));
            searchMRSpouseSurnameField.sendKeys("Козека", Keys.ENTER);

            WebElement searchMRSpouseNameField = driver.findElement(By.xpath("//input[@id=(//label[contains(., 'Имя супруга/и')]/@for)]"));
            searchMRSpouseNameField.sendKeys("Екатерина", Keys.ENTER);

            WebElement searchMRSpouse2ndNameField = driver.findElement(By.xpath("//input[@id=(//label[contains(., 'Отчество супруга/и')]/@for)]"));
            searchMRSpouse2ndNameField.sendKeys("Вячеславовна", Keys.ENTER);

            WebElement searchMRSpouseDateOfBirth = driver.findElement(By.xpath("//input[@id=(//label[contains(., 'Дата рождения супруга/и')]/@for)]"));
            searchMRSpouseDateOfBirth.sendKeys("27012004", Keys.ENTER);

            WebElement searchMRSpousePassportNumberField = driver.findElement(By.xpath("//input[@id=(//label[contains(., 'Номер паспорта супруга/и')]/@for)]"));
            searchMRSpousePassportNumberField.sendKeys("АВ2222222", Keys.ENTER);

            Thread.sleep(2000);

            WebElement buttonMRFinishByText = driver.findElement(By.xpath("//*[text()=\"Завершить\"]"));
            buttonMRFinishByText.click();

            Thread.sleep(2000);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
    }
}