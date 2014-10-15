Attempt to Retrieve an Itinerary using an invalid PNR on the the Travel Funds landing page

Meta:
@flow air
@process view
@user anonymous
@traveler anonymous
@message This story REQUIRES that the CAPTCHA off toggle be used (-DCAPTCHA_Off), this may require changes to jobs run on Jenkins
@not_passing draft

Narrative: In order to see the OOPS message
As a user
I attempt to retrieve an Itinerary using an invalid PNR on the Travel Funds landing page


Scenario: Attempt to Retrieve an Itinerary using an invalid PNR on the Travel Funds landing page (Scenario 11)

Given I am on the Travel Funds Page
When I attempt to retrieve an itinerary using an invalid PNR on the Travel Funds Page
Then I view the OOPS message for the invalid PNR using travel funds
