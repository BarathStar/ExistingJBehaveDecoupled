Reserve a Hotel Room with Hibernate Exception

Meta:
@flow hotel
@suite faultInjectionServiceTier
@process reservation
@user anonymous
@not_passing draft

Narrative:
As a system professional
I want to inject a Hibernate exception
In order to verify proper error handling for booking hotel reservation

Scenario: Customer Reserve Hotel with Hibernate exception
Given I have the following hotel itinerary data:
        |Field|Value|
        |destination|DAL|
        |checkInDate|+2|
        |checkOutDate|+4|
And I am a customer looking for a hotel
And I am injecting a ./src/main/stories/fault_injection/btm/HotelReservation_hibernateException.btm fault
When I reserve a hotel room
Then I receive a hotel confirmation number
And I unload all byteman rules