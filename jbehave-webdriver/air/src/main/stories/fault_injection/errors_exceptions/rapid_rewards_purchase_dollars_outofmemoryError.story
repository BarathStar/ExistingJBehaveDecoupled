Injecting out of memory in the rapid rewards purchase flow

Meta:
@suite faultInjection
@flow air
@process booking
@user anonymous
@traveler adult
@not_passing broken

Narrative:
As a system professional
I want to inject an OutOfMemory error
In order to ensure the proper error handling is invoked

Scenario: Rapid Rewards Member air purchase
Given I am flying a round-trip Southwest Southwest flight
And I am injecting a ./src/main/stories/fault_injection/btm/PurchaseConfirmationServer_outofmemory.btm fault
When I log in from the account sidebar at the Search Flights page
And I book a flight
Then I see out of memory oops
