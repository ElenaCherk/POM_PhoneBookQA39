package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ContactListScreen extends BaseScreen{

    public ContactListScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']"+ "/android.widget.TextView")
    MobileElement activityTextView;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/title']")
    MobileElement logoutButton;
    @FindBy(xpath = "//*[@content-desc='More options']")
    MobileElement moreOption;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/add_contact_btn']")
    MobileElement plusButton;
    @FindBy(xpath = "//*[@resource-id='android:id/message']")
    MobileElement errorTextView;

    @FindBy(xpath = "//*[@resource-id='android:id/button1']")
    MobileElement okButton;

    public boolean isContactListActivityPresent(){
        return shouldHave(activityTextView,"Contact list",5);
    }

    public AuthenticationScreen logout(){
        if(isDisplayedWithException(moreOption)) {
            moreOption.click();
            logoutButton.click();
        }
        return new AuthenticationScreen(driver);
    }

    public ContactListScreen assertContactListActivityPresent(){
        Assert.assertTrue(isContactListActivityPresent());
        return this;
    }

    public AddNewContactScreen openContactForm() {
        waitElement(plusButton, 5);
        plusButton.click();
        return new AddNewContactScreen(driver);
    }

    public ContactListScreen isErrorMassageContainsText(String text) {
        Assert.assertTrue(errorTextView.getText().contains(text));
        okButton.click();
        return this;
    }

    public ContactListScreen isErrorMessageContainsTextInAlert(String text) {
        Alert alert = new WebDriverWait(driver,3)
                .until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains(text));
        alert.accept();
        return this;
    }
}