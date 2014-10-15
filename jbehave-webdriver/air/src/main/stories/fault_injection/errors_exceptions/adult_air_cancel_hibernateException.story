Cancel flight as an adult

Meta:
@suite faultInjection
@flow air
@process cancel
@user anonymous
@traveler adult

Narrative:
In order to cancel my boarding pass
As an adult
I want to cancel my flight

Scenario: adult air cancel
Given I am flying a round-trip Southwest Southwest flight
And I have a flight booked
And I am injecting a ./src/main/stories/fault_injection/btm/CancelReservationService_hibernate.btm fault
When I cancel the flight
Then I verify hibernate fault results