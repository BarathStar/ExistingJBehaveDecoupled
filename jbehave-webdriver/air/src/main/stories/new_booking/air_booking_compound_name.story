Purchase a round-trip anytime air ticket for an adult and
verify compound names containing spaces are printed correctly on Confirmation Page



Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@project_AccordionPage

Narrative:
In order to fly on a date that I want
As an adult with a compound name
I want to book a flight and
receive a confirmation number

Scenario: print compound passenger names with spaces correctly on Confirmation Page
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

When I have compound name and book a flight from search flights page
Then I should see the confirmation page with my compound first and compound last name
