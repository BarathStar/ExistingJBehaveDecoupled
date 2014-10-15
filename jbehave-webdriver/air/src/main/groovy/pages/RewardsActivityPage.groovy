package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import static org.junit.Assert.fail

class RewardsActivityPage extends BasePage {

    private static final String relativePath = "account/rapidrewards/rewards-activity"
    private static final By ACTIVITY_TABLE = By.className("activity_table")
    private static final By ACTIVITY_DESCRIPTION = By.className("description")
    private static final By FULL_ACTIVITY_TABLE = By.xpath("//table[@class='activity_table']//tr")

    def RewardsActivityPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider, relativePath)
    }

    @Override
    String getRelativePath() {
        return relativePath
    }

    def verifyPage() {
        verifyCurrentPageLocation()
    }

    def verifyAPlusDebitTransactionInActivityDetails() {
        waitForElement(ACTIVITY_TABLE).findElements(ACTIVITY_DESCRIPTION)[0].getText().shouldContain "TRANSFER A+ Credits to Rapid Rewards Credits\n2 credits to 2 credits", "Rewards Activity Details do not contain the Aplus debit transaction for 2 credit(s)"
    }

    def verifyPointsReversalTransactionInActivityDetails() {
        waitForElement(ACTIVITY_TABLE).findElements(ACTIVITY_DESCRIPTION)[1].getText().shouldContain "CONVERSION - Transaction Error\nRedeposit 300 Rapid Rewards Points", "Reward points reversal transaction not on history page"
    }
    def verifyAwardsReversalTransactionInActivityDetails() {
        waitForElement(ACTIVITY_TABLE).findElements(ACTIVITY_DESCRIPTION)[2].getText().shouldContain "CONVERSION - Transaction Error\nRedeposit Rapid Rewards Standard Award #12345", "Reward award reversal transaction not on history page"
    }

    def verifyElementsOnActivityTable() {
        List<WebElement> table = waitForElements(FULL_ACTIVITY_TABLE)
        if (table.size() <= 1) {
            fail('The table Rewards Activity Details is empty. It should contain at least one element.')
        }
    }
}
