Purchase a round-trip business select air ticket for an adult

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@removedFromAirbooking
@dyna_stubs
@project_AccordionPage

Narrative:
In order to fly on a date that I want
As an adult
I want to book a flight using business select and
receive a confirmation number

Scenario: adult air business select purchase
Given I am traveling as a:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|ANY|
    |arrivalStation|ANY|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|BusinessSelect|
    |inboundRouting|Nonstop|

When I search and book a flight from search flights page
Then I receive an air confirmation number
And I should not see the Early Bird upsell button
