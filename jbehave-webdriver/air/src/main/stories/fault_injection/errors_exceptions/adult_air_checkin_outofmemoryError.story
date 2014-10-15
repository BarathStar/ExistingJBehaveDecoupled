Adult Air Checkin

Meta:
@suite faultInjection
@flow air
@process checkin
@user anonymous
@traveler adult

Narrative:
In order to receive my boarding pass
As an adult
I want to checkin for a flight


Scenario: Adult Checkin

Given I am flying a round-trip Southwest Southwest flight
And I book a flight eligible for checkin 1 adult
And I am injecting a ./src/main/stories/fault_injection/btm/RetrieveReservationServiceImpl_outofmemory.btm fault
When I click on the Check in button on the Confirmation Page
Then I see out of memory oops