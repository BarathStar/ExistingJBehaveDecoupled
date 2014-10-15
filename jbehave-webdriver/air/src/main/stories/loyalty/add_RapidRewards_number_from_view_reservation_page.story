Verify Add Rapid Rewards link in view reservation page

Meta:
@flow rr
@process loyalty
@user anonymous
@traveler adult
@dyna_stubs
@project avengers_13.6
@project_in_dev
@storyid AV-1094

Narrative:
As an anonymous  user
I want to book a flight and verify Add Rapid Rewards Number link in view reservation page

Scenario: User clicks the Add Rapid Rewards number link from view reservation page and it navigates to Add Rapid Rewards number page.

Given I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|SAT|
    |arrivalStation|HOU|
    |departingFlight_carrierCode|FL|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|WannaGetAway|
    |inboundRouting|Nonstop|

And I have a flight booked
And I retrieve my itinerary
When I click Add Rapid Rewards Number Link in the view Reservation page
Then I should be in the Add Rapid Rewards Number page
And I see confirmation number and name pre populated in Add Rapid Rewards Number page
