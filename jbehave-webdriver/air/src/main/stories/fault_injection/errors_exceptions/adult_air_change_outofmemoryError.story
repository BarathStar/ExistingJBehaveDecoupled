Injecting hibernate exception in the purchase flow

Meta:
@suite faultInjection
@flow air
@process change
@user anonymous
@traveler adult

Narrative:
As a system professional
I want to inject a hibernate exception
In order to ensure the proper error handling is invoked

Scenario: adult air change

Given I am flying a round-trip Southwest Southwest flight
And I have a flight booked
And I am injecting a ./src/main/stories/fault_injection/btm/PurchaseConfirmationServer_outofmemory.btm fault
When I change the flight to a later date
Then I see out of memory oops
