package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import util.NumberToWords

public class NonRevListPassengersPage extends BasePage {

    private static final By ADD_BUDDY_PASS_BUTTON = By.className("swa_feature_nonRev_list_buddyPasses_buddy_addButton")
    private static final By CREATE_LISTING_BUTTON = By.id("createNonRevListing")
    private static final By BUDDY_RADIO_BUTTON = By.id("buddy")
    private static final By PHONE_AREA_CODE = By.id("phoneNumber1")
    private static final By PHONE_AREA_EXCHANGE_CODE = By.id("phoneNumber2")
    private static final By PHONE_AREA_STATION_CODE = By.id("phoneNumber3")
    private static final By ADD_ANOTHER_EMAIL_ADDRESS = By.id("addAnotherEmailAddress")
    private static final By CONFIRMATION_EMAIL = By.id("eMailSendConfirmation")

    public NonRevListPassengersPage(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.
    }

    public void selectBuddyPasses() {
        waitForElement(BUDDY_RADIO_BUTTON).click()
    }

    public void fillOutBuddyInformation(int position) {
        WebElement firstName = waitForElement(By.name("buddyPasses[$position].firstName"))
        WebElement lastName = waitForElement(By.name("buddyPasses[$position].lastName"))
        WebElement birthday = waitForElement(By.name("buddyPasses[$position].birthday"))
        WebElement gender = waitForElement(By.name("buddyPasses[$position].gender"))

        String positionString = NumberToWords.convert(position).replace(/ /, '');

        firstName.sendKeys("first$positionString")
        lastName.sendKeys("last$positionString")
        birthday.sendKeys("01/01/1980")
        gender.sendKeys("M")
    }

    public int getCurrentBuddyPassCount() {
        List<WebElement> buddyPassRows = buddyPassesTableBody.findElements(By.tagName("tr"))
        return buddyPassRows.size()
    }

    public int getCurrentShareItineraryEmailCount() {
        WebElement itineraryShareEmailContainer = waitForElement(By.className("swa_feature_nonRev_emailItinerary_inputs"))
        List<WebElement> elements = itineraryShareEmailContainer.findElements(By.className("swa_email"))
        return elements.size()
    }

    public void addBuddyRow() {
        waitForElement(ADD_BUDDY_PASS_BUTTON).click()
    }

    public void addShareItineraryEmail() {
        waitForElement(ADD_ANOTHER_EMAIL_ADDRESS).click()
    }

    public void fillContactInformation() {
        enterPhoneNumber()
        enterConfirmationEmail()
    }

    private void enterPhoneNumber() {
        waitForElement(PHONE_AREA_CODE).sendKeys("214")
        waitForElement(PHONE_AREA_EXCHANGE_CODE).sendKeys("123")
        waitForElement(PHONE_AREA_STATION_CODE).sendKeys("4567")
    }

    private void enterConfirmationEmail() {
        waitForElement(CONFIRMATION_EMAIL).sendKeys("test@test.com")
    }

    public void enterShareItineraryEmail(int zeroBasedPosition) {
        int oneBasedPosition = zeroBasedPosition + 1
        WebElement email = waitForElement(By.id("eMailItinerary$oneBasedPosition"))
        email.sendKeys("test$oneBasedPosition@test.com")
    }

    public void createListing() {
        waitForElement(CREATE_LISTING_BUTTON).submit()
    }

    public void addBuddies(int buddyCount) {
        selectBuddyPasses()

        for (int i = getCurrentBuddyPassCount(); i < buddyCount; i++) {
            addBuddyRow()
        }

        for (int i = 0; i < buddyCount; i++) {
            fillOutBuddyInformation(i)
        }
    }

    public void enterContactInformation(int itineraryCopies) {

        for (int i = getCurrentShareItineraryEmailCount(); i < itineraryCopies; i++) {
            addShareItineraryEmail()
        }

        for (int i = 0; i < itineraryCopies; i++) {
            enterShareItineraryEmail(i)
        }

        fillContactInformation()
    }

    public void submitForm() {
        createListing()
    }

}
