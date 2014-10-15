Apply travel funds to booking and change flight

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@not_passing draft

Narrative:
In order to use my travel funds
As an adult
I want to book a flight using travel funds and change the flight again

Scenario: Apply Travel Funds
Given I am traveling as a:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

When I book a flight with travel funds
Then I should receive an air confirmation
And I see travel funds applied
When I fill in the Purchase form and submit
Then I receive an air confirmation number
Given I am on the Search Flight page
When I change the flight from search flight page
Then I should see the itinerary change confirmation page
