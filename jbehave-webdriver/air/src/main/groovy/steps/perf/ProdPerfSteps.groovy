package steps.perf

import org.jbehave.core.annotations.BeforeStories
import org.jbehave.core.annotations.When
import org.jbehave.core.annotations.Then

import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.Pending

class ProdPerfSteps {
    private long startTime;

    @BeforeStories
    def beforeStories() {
        int i = 1
    }


    @Given("Start Counting")
    @When("Start Counting")
    @Then("Start Counting")
    def startCounting() {
        startTime = System.currentTimeMillis()
    }

    @Given("Show Time Delta")
    @When("Show Time Delta")
    @Then("Show Time Delta")
    def showTime() {
        println System.currentTimeMillis() - startTime
    }

    @Given("I have an open browser")
    @Pending
    public void givenIHaveAnOpenBrowser(){
        print "givenIHaveAnOpenBrowser"
      // PENDING
    }

    @Given("I start timing")
    @Pending
    public void givenIStartTiming(){
      // PENDING
    }

    @When("I navigate to the southwest.com homepage")
    @Pending
    public void whenINavigateToTheSouthwestcomHomepage(){
      // PENDING
    }

    @When("I can interact with the flight booking widget")
    @Pending
    public void whenICanInteractWithTheFlightBookingWidget(){
      // PENDING
    }

    @Then("report the all times")
    @Pending
    public void thenReportTheAllTimes(){
      // PENDING
    }


}
