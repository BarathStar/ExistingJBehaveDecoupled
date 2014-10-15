Verify go to BUG page from Swabiz Search Flight page

Meta:
@project coda
@project_in_dev
@flow air
@process booking swabiz
@traveler adult
@user swabiz_anonymous
@dyna_stubs

@project bookingWidget

Narrative:
In order to show the booking flow for Southwest destinations
As a Customer
I want to select an Southwest market pair that are serviced by swabiz.com


Scenario: An adult searches for an Southwest flight on the Swabiz Search Flight page

Given I have anonymously logged into a SWABiz account

And I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |arrivalStation|AUS|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

And The following routes are available:

    |Field|Value|
    |originStation|HOU|
    |destinationStation|AUS|
    |carrierDates|WN:T:[today,today+60]|

When I enter my flight information
Then Verify that I am on the Select Flights page
