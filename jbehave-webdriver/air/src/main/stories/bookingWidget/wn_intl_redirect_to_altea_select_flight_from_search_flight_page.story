Verify go to Altea web site from Search Flight page

Meta:
@project coda
@project_in_dev
@flow air
@process booking
@traveler adult
@dyna_stubs
@not_live
@not_passing draft

@project bookingWidget
@note this test needs to have Altea web server running

Narrative:
In order to show the booking flow for Southwest International destinations
As a Customer
I want to select an Southwest International market pair that are serviced by global.southwest.com


Scenario: An adult searches for an Southwest International flight on the Search Flight page

Given I am:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have selected the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |arrivalStation|GDL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

And The following routes are available:
    |Field|Value|
    |originStation|HOU|
    |destinationStation|GDL|
    |carrierDates|WN:F:[today,today+60]|
And The following routes are available:
    |Field|Value|
    |originStation|GDL|
    |destinationStation|HOU|
    |carrierDates|WN:F:[today,today+60]|

When I successfully search for flights from the flight search page
Then Verify that correct redirect url is created for Altea web site
