package steps


import org.jbehave.core.annotations.*
import pages.SWAPricePage
class SWAPriceSteps
{

    SWAPricePage swaPricePage

    @Then("I view my price details")
    def verifyPrice()
    {


        swaPricePage.verifyPriceDetails()

    }




}