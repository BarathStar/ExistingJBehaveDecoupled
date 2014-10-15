Verify on Add Rapid Rewards Number page after enrollment redirect

Meta:
@flow rr
@process loyalty
@user anonymous
@traveler adult
@passenger adult
@dyna_stubs
@project avengers_13.11
@project_in_dev
@storyid AV-2395

Narrative:
As an anonymous user
I want to book a flight for 2 more passengers and I am on Add Rapid Rewards Number page after enrollment.


Scenario: User click the enroll icon on Add Rapid Rewards Number page and after enrollment it redirects to Add Rapid Rewards Number page

Given I am flying with the following passenger: John Smith
And I am flying with the following passenger: Smith Johns
And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |departureDate|+1|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

And I have finished my purchase adding my products to a new trip named My Trip
And I am on the Add Rapid Rewards Number Page
When I click on the wcm image for enrollment
And I complete all the mandatory fields for John Smith
Then I should be in the Add Rapid Rewards Number page
And I see first name and last name populated in Add Rapid Rewards Number page
