Cancel a Oneway Anytime air ticket booked with CC and promocode for an anonymous adult

Meta:
@bugpodCoreRegression
@flow air
@process cancel
@user anonymous
@traveler adult
@storyId MH-1086

GivenStories:
bugpod/Air_Booking_PromoCode_CC_Anon_adult_OW_BS_NS.story

Scenario: An anonymous user cancel the flight

Given I am on the Home page
When I access the cancel air reservation option
And I attempt to cancel my air reservation
And I confirm the air cancellation
Then I see the Cancellation Confirmation Page