Verify AirTran Business Select shows a message when booking two seats as a customer of size

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@user
@traveler adult
@storyId DCAIR-5422
@dyna_stubs
@project_AccordionPage

Narrative: In order to not waste my money on an unnecessary seat
As a customer of size booking two COS seats in a round-trip AirTran Business Select itinerary
I want to see messaging indicating that a COS only needs to purchase one Business Select seat with AirTran

Scenario: AirTran Business Select should show a message when booking two seats as a customer of size

Given I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|BOS|
    |arrivalStation|BWI|
    |departingFlight_carrierCode|FL|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|FL|
    |arrivingFlight_fareClass|BusinessSelect|
    |inboundRouting|Nonstop|

And I expect that booking a flight will fail
When I book a flight as a POS
Then I should see a message about not needing to buy an extra seat
