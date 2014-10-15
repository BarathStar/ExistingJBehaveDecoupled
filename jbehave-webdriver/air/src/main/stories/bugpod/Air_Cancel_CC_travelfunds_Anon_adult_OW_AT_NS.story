Cancel the anonymous oneway nonstop air booking PNR2 with the payment details - CC, travel fund from the previous booking PNR1 -OW,WGA,NS with CC

Meta:
@bugpodCoreRegression
@flow air
@process cancel
@user anonymous
@traveler adult
@storyId MH-1072

GivenStories:
bugpod/Air_Booking_CC_Travelfunds_Anon_adult_OW_AT_NS.story

Scenario: An anonymous user cancel the flight

Given I am on the Home page
When I access the cancel air reservation option
And I attempt to cancel my air reservation
And I confirm the air cancellation
Then I see the Cancellation Confirmation Page
