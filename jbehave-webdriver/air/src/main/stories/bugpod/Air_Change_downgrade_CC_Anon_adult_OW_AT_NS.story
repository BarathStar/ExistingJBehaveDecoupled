Downgrade change for anonymous adult for a OW nonstop flight from Business select to Anytime fare

Meta:
@bugpodCoreRegression
@flow air
@process change
@user anonymous
@traveler adult
@storyId MH-1067

GivenStories:
bugpod/Air_Booking_PromoCode_CC_Anon_adult_OW_BS_NS.story

Scenario: A anonymous user downgrade change

Given I am on the Home page
When I downgrade the flight to Anytime fare and LAX as arrival city
Then I see the itinerary changed page
When I retrieve my itinerary
Then I view my itinerary