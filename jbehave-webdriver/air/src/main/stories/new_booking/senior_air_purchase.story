Purchase a Senior Air Ticket

Meta:
@flow air
@process booking
@user anonymous
@traveler senior
@removedFromAirbooking
@dyna_stubs
@project_AccordionPage
@project_lfcsops_not_ready

Narrative:
In order to fly on a date that I want
As a senior
I want to book a flight and
receive a confirmation number

Scenario: senior air purchase
Given I am traveling as a:
    |Field|Value|
    |adultPassengerCount|0|
    |seniorPassengerCount|1|

And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Senior|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Senior|
    |inboundRouting|Nonstop|

When I search and book a flight from search flights page
Then I receive an air confirmation number
