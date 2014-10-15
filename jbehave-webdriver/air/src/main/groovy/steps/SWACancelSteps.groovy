package steps


import pages.SWACancelPage
import org.jbehave.core.annotations.*
class SWACancelSteps
{
    private boolean returnFundsToCreditCard
    SWACancelPage cancelPage


    @When("I cancel the flight")
    def cancelFlight()
    {
       returnFundsToCreditCard = true
        cancelPage.cancelFlight(returnFundsToCreditCard)
        cancelPage.confirmAirCancellation()
    }


}