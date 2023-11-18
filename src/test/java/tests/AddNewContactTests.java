package tests;

import config.AppiumConfig;
import models.Contact;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

public class AddNewContactTests extends AppiumConfig {
    int i;
    @BeforeMethod
    public void precondition(){
        i = (int)(System.currentTimeMillis()/1000)%3600;
        new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .fillEmail("rita@mail.com")
                .fillPassword("Rr12345$")
                .submitLogin();
    }
    @Test
    public void addNewContactPositive(){
        Contact contact = Contact.builder()
                .name("AddNewContact_"+ i)
                .lastName("Positive")
                .email("addNewContact_" +i+"@mail.com")
                .phone("1234567"+i)
                .address("Ashdod")
                .description("NewContact"+ i)
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm();
    }
    @Test
    public void addNewContactNegativeNameBlank(){
        Contact contact = Contact.builder()
                .name("")
                .lastName("Negative")
                .email("addNewContact_" +i+"@mail.com")
                .phone("1234567"+i)
                .address("Ashdod")
                .description("NewContact"+ i)
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isErrorMessageContainsTextInAlert("blank");
    }
    @Test
    public void addNewContactNegativeLastNameBlank(){
        Contact contact = Contact.builder()
                .name("AddNewContact_"+ i)
                .lastName("")
                .email("addNewContact_" +i+"@mail.com")
                .phone("1234567"+i)
                .address("Ashdod")
                .description("NewContact"+ i)
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isErrorMessageContainsTextInAlert("blank");
    }
    @Test
    public void addNewContactNegativeWrongEmail(){
        Contact contact = Contact.builder()
                .name("AddNewContact_"+ i)
                .lastName("Negative")
                .email("addNewContact_" +i+"mail.com")
                .phone("1234567"+i)
                .address("Ashdod")
                .description("NewContact"+ i)
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isErrorMessageContainsTextInAlert("email address");
    }
    @Test
    public void addNewContactNegativeWrongPhone(){
        Contact contact = Contact.builder()
                .name("AddNewContact_"+ i)
                .lastName("Negative")
                .email("addNewContact_" +i+"@mail.com")
                .phone("12Gh")
                .address("Ashdod")
                .description("NewContact"+ i)
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isErrorMessageContainsTextInAlert("only digits");
    }
    @Test
    public void addNewContactNegativeAddressBlank(){
        Contact contact = Contact.builder()
                .name("AddNewContact_"+ i)
                .lastName("Negative")
                .email("addNewContact_" +i+"@mail.com")
                .phone("1234567"+i)
                .address("")
                .description("NewContact"+ i)
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isErrorMessageContainsTextInAlert("blank");
    }
 
}