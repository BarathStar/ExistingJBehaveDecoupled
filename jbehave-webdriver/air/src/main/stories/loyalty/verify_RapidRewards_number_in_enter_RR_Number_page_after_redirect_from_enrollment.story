Verify I am on Enter Rapid Rewards Number Page after enrollment in RR Account from enroll Icon

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
As a Rapid Rewards Member
I want to book a flight for 2 more passengers and verify Enter Rapid Rewards Number link in Add Rapid Rewards Number Confirmation page.


Scenario: User click the Add Rapid Rewards Number link on Enter Rapid Rewards Number Confirmation page and it navigates to Enter Rapid Rewards number page.

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
When I enter my PNR on the add Rapid Rewards number page
And I click on the wcm image for enrollment
And I complete all the mandatory fields for John Smith
Then I should be on the Enter Rapid Rewards number page
