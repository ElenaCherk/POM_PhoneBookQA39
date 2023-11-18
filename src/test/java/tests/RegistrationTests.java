package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

public class RegistrationTests extends AppiumConfig {
    int i;
    @BeforeMethod
    public void precondition(){
         i = (int)(System.currentTimeMillis()/1000)%3600;
    }
    @Test
    public void registrationPositive(){
        new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .fillEmail("rina" + i + "@mail.com")
                .fillPassword("Rr12345$")
                .submitRegistration()
                .assertContactListActivityPresent();
    }

    @Test
    public void registrationModel(){
        Assert.assertTrue(
                new SplashScreen(driver)
                        .gotoAuthenticationScreen()
                        .registration(
                                Auth.builder()
                                        .email("rina" + i + "@mail.com")
                                        .password("Rr12345$")
                                        .build())
                        .isContactListActivityPresent());
    }

    @Test
    public void registrationWrongEmail(){
        new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .fillEmail("rina_" + i + "mail.com")
                .fillPassword("Rr12345$")
                .submitRegistrationNegative()
                .isErrorMassageContainsText("email address");
    }
    @Test
    public void registrationWrongPassword(){
        new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .registrationNegative(
                        Auth.builder()
                                .email("rina" + i + "@mail.com")
                                .password("Rr123")
                                .build())
                .isErrorMessageContainsTextInAlert("password"); // можно так
    }
    @AfterMethod
    public void postCondition(){
        new ContactListScreen(driver).logout();
        new SplashScreen(driver);
        }
    }

