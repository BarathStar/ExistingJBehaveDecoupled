Injecting out of memory in the purchase flow

Meta:
@suite faultInjection
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
As a system professional
I want to inject an OutOfMemory error
In order to ensure the proper error handling is invoked

Scenario: adult air purchase
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

And I am injecting a ./src/main/stories/fault_injection/btm/PurchaseConfirmationServer_outofmemory_dynaStubs.btm fault
When I search and book a flight from search flights page
Then I verify web metrics value
