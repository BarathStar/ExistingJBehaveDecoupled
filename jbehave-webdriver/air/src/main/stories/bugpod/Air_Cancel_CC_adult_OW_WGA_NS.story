Cancel a OW PNR for anonymous adult with WGA fare.

Meta:
@bugpodCoreRegression
@flow air
@process change
@user anonymous
@traveler adult
@not_passing outOfScope

GivenStories:
bugpod/Air_Booking_CC_Anon_adult_OW_WGA.story

Scenario: An anonymous user cancel the flight

Given I am on the Home page
When I access the cancel air reservation option
And I attempt to cancel my air reservation
And I confirm the air cancellation
Then I see the Cancellation Confirmation Page
