Apply travel funds to booking and cancel flight

Meta:
@flow air
@process cancel
@user anonymous
@traveler adult
@not_passing draft

Narrative:
In order to use my travel funds
As an adult
I want to book a flight using travel funds and cancel the flight

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
When I cancel the flight and hold funds
Then I view the flight cancellation confirmation
And I see funds been held for future use
Given I am on the Travel Funds Page
When I enter PNR on view travel funds page
Then I see available travel funds
