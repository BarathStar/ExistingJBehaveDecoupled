package pages.elements

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import pages.BasePage
import util.ChaseSimulatorUrlCreator
import com.swacorp.dotcom.webscenarios.air.config.Domains
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad


class Chase extends BasePage {

    private static final By CHASE_AD = By.id("chase_ad")
    private static final By CREDIT_EXCEEDED_MESSAGE_BOX = By.id("creditExceededMessageBox")
    private static final By CREDIT_CARD_ZIP1 = By.id("creditCardZip1")
    private static final By CREDIT_CARD_ZIP2 = By.id("creditCardZip2")
    private static final String REDIRECT_URL_STRING = "/reservations/price-reservations.html"
    private String zipCode = "75235"
    private int incrementVal = 1000

    public Chase(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.
    }

    def simulatePendingStatusChaseApplication(){
        String approvedPriceString = extractPriceAndIncrement(incrementVal)
        simulateChaseApplication("P", approvedPriceString, zipCode, false)
    }

    def simulateApprovedStatusChaseApplication(){
        String approvedPriceString = extractPriceAndIncrement(incrementVal)
        simulateChaseApplication("Ap", approvedPriceString, zipCode, false)
    }

    def simulatePartialApprovalChaseApplication(){
        incrementVal = -10
        String approvedPriceString = extractPriceAndIncrement(incrementVal)
        simulateChaseApplication("Ap", approvedPriceString, zipCode, false)
    }

    def simulateNineDigitZipChaseApplication(){
        String approvedPriceString = extractPriceAndIncrement(incrementVal)
        zipCode = "752351234"
        simulateChaseApplication("Ap", approvedPriceString, zipCode, false)
    }

    def simulateChaseApplicationWithCyberArkUnavailable(){
        String approvedPriceString = extractPriceAndIncrement(incrementVal)
        simulateChaseApplication("Ap", approvedPriceString, zipCode, true)
    }


    def simulateChaseApplication( String approvalStatus, String priceString, String zipCode, boolean encryptionFailed){
        String urlString = getCurrentUrl().toString()
        def session = extractSession(urlString, "disc")


        ChaseSimulatorUrlCreator chaseSimulatorUrlCreator = new ChaseSimulatorUrlCreator()
		String chaseUrlString =  chaseSimulatorUrlCreator.createApprovedUrl(session, priceString, approvalStatus, zipCode, encryptionFailed)
        get(Domains.dotcomDomain + chaseUrlString)
        WaitForPageToLoad

        String chaseReturnUrlRaw = findElement(By.tagName("pre")).text
        String chaseReturnUrl = chaseReturnUrlRaw.trim().replace("STATUS=SUCCESS&","").replace('^',":")
        get(chaseReturnUrl)
        WaitForPageToLoad
    }

    private def extractSession(String urlString, String keyString) {
        def url = new URL(urlString)
        def map = url.query.split('&').inject([:]) {map, kv -> def (key, value) = kv.split('=').toList(); map[key] = value != null ? URLDecoder.decode(value) : null; map }
        return map[keyString]
    }

    private String extractPriceAndIncrement(incrementVal) {
        String priceString = findElement(By.id("totalCartPrice")).text.trim().replace("\$", "")
        int approvedPrice = priceString.toDouble().intValue() + incrementVal
        String approvedPriceString = approvedPrice.toString()
        return approvedPriceString
    }

    def verifyIntermediaryOptionsPage(){
        waitForElement(CREDIT_EXCEEDED_MESSAGE_BOX).getText().shouldContain "Purchase Price Exceeds Temporary Credit Limit"
    }

    def cancelApplicationAndRedirect(){
        get(Domains.dotcomDomain + REDIRECT_URL_STRING)
    }

    def verifyPricePage(){
        getCurrentUrl().shouldContain REDIRECT_URL_STRING
    }

    def verifyFiveDigitZip1(){
        waitForElement(CREDIT_CARD_ZIP1).getAttribute("value").shouldBe "75235"
    }

    def verifyFourDigitZip2(){
        waitForElement(CREDIT_CARD_ZIP2).getAttribute("value").shouldBe "1234"
    }
}
