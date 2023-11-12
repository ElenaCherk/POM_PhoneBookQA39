package tests;

import config.AppiumConfig;
import org.testng.annotations.Test;
import screens.SplashScreen;

public class RegistrationTests extends AppiumConfig {
    @Test
    public void registrationPositive(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .fillEmail("rina" + i + "@mail.com")
                .fillPassword("Rr12345$")
                .submitReg();
    }
}
