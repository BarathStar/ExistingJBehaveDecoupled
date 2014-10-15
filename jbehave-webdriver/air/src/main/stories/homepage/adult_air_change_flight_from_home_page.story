Adult Air Change Flight from Home Page Account Bar

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to change my flight
As an adult
I want to change a flight from the account bar


Scenario: Adult Change Flight

Given I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And air itinerary data as follows:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|DAL|
    |arrivalStation|HOU|
    |departingFlight_carrierCode|WN|
    |arrivingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |adultPassengerCount|1|
    |departureDate|+7|

When I search and book a flight from search flights page
And I am on the Home page
And I click on the Change Flight Section of the Account Bar or Change Flight Link on Home Page
And I enter the confirmation number, first name, last name, and click continue
And I change the flight
Then I should see the itinerary change confirmation page