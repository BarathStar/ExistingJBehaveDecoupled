Downgrade change for anonymous adult for a OW nonstop flight from Business select to Anytime fare

Meta:
@bugpodCoreRegression
@flow air
@process change
@user rr_member
@traveler adult
@storyId MH-1088

GivenStories:
bugpod/Air_Booking_Points_RR_Adult_OW_BS_NS_valid_for_WGA.story

Scenario: A anonymous user downgrade change

Given I am on the Home page
When I downgrade the flight to Wanna Get Away fare
Then I see the itinerary changed page
When I retrieve my itinerary
Then I view my itinerary