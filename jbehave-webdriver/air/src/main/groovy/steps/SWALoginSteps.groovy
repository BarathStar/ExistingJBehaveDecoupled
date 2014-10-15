package steps


import org.jbehave.core.annotations.*
import pages.SWALoginPage

class SWALoginSteps
{
    SWALoginPage  swaloginPage

   @Given("I login as \$LoginType")
    def loginAsMemberType(@Named("LoginType") String login_type)
    {
        swaloginPage.login(login_type)
    }


  }

