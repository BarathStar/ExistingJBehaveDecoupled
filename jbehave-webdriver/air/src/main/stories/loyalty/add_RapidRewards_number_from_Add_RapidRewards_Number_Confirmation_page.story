Verify Add Rapid Rewards Number link in Add Rapid Rewards Number confirmation page

Meta:
@flow rr
@process loyalty
@user RR_member
@global_nav_regression
@traveler adult
@dyna_stubs
@project avengers_13.6
@project_in_dev
@storyid AV-1457

Narrative:
As a Rapid Rewards Member
I want to book a flight for 2 more passengers and verify Add Rapid Rewards Number link in Add Rapid Rewards Number Confirmation page.


Scenario: User click the Add Rapid Rewards Number link on Add Rapid Rewards Number Confirmation page and it navigates to Enter Rapid Rewards number page.

Given I am a Rapid Rewards Member passenger
And I have 2 more passengers traveling with me
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
And I enter my Rapid Rewards Number 22 on the add Rapid Rewards Number page at Passenger 2
And I click Add Rapid Rewards Number Link in the Add Rapid Rewards Number confirmation page
Then I should be on the Enter Rapid Rewards number page