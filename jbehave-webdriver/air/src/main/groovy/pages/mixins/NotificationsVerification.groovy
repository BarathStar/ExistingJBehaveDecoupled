package pages.mixins

import org.openqa.selenium.By
import pages.BasePage
import org.openqa.selenium.WebElement

public class NotificationsVerification {
    private static final By NOTIFICATION_CONTAINER = By.id("notification_wrapper")

    def checkNotificationServed() {
        isElementDisplayed(NOTIFICATION_CONTAINER)
    }

    public def verifyNotificationServed(String notificationMsg) {
        verifyNotificationContainer(true, notificationMsg)
    }

    public def verifyNotificationNotServed(String notificationMsg) {
        verifyNotificationContainer(false, notificationMsg)
    }

    private boolean verifyNotificationContainer(boolean shouldBePresent, String notificationMsg) {
        if (shouldBePresent) {
            waitForElement(NOTIFICATION_CONTAINER).getText().shouldContain notificationMsg, "The notification should contain : " + notificationMsg
        } else {
            WebElement notificationContainer = waitForElement(NOTIFICATION_CONTAINER, false, BasePage.DEFAULT_WAIT_LIMIT_FOR_ELEMENT_THAT_MAY_EXIST)
            if (notificationContainer != null && notificationContainer.isDisplayed()) {
                if (notificationContainer.getText().indexOf(notificationMsg) > -1) {
                    fail "A notification was displayed : " + notificationMsg
                }
            }
        }
    }

}
