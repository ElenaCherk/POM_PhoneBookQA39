package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.Contact;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddNewContactScreen extends BaseScreen{

    public AddNewContactScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputName']")
    MobileElement nameEditText;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputLastName']")
    MobileElement lastNameEditText;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputEmail']")
    MobileElement emailEditText;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputPhone']")
    MobileElement phoneEditText;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputAddress']")
    MobileElement addressEditText;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputDesc']")
    MobileElement descriptionEditText;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/createBtn']")
    MobileElement createButton;
    public AddNewContactScreen fillContactForm(Contact contact){
        waitElement(nameEditText,7);
        type(nameEditText,contact.getName());
        type(lastNameEditText,contact.getLastName());
        type(emailEditText,contact.getEmail());
        type(phoneEditText,contact.getPhone());
        type(addressEditText,contact.getAddress());
        type(descriptionEditText,contact.getDescription());
       return this;
    }

    public ContactListScreen submitContactForm(){
    createButton.click();
    return new ContactListScreen(driver);
    }
}