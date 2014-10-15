Upcoming trips

Meta:
@suite regression
@flow air
@Vipul
@dyna_stubs
@not_passing draft

Narrative:
In order to view my upcoming trips
As an rapid rewards member
I login to my account


Scenario: View upcoming trips

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

And I am logged in as a valid Rapid Rewards member on the Search Flights page
When I search and book a flight from search flights page
Then I receive an air confirmation number
When I click my account link
And I click to see details of my upcoming trip
Then I verify trip details
