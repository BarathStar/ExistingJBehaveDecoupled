Upgrade change for anonymous adult for a OW nonstop flight from WGA to Anytime fare

Meta:
@bugpodCoreRegression
@flow air
@process change
@user anonymous
@traveler adult
@storyId MH-1077

GivenStories:
bugpod/Air_Cancel_CC_adult_OW_WGA_NS.story,
bugpod/Air_Booking_CC_Anon_adult_OW_WGA.story

Scenario: A anonymous user upgrade change

Given I am on the Home page
When I upgrade the flight to Anytime fare with travel funds and PHL as arrival city
Then I see the itinerary changed page
When I retrieve my itinerary
Then I view my itinerary