Injecting hibernate exception in the purchase flow for trip management

Meta:
@suite faultInjectionServiceTier
@flow air
@process booking
@user anonymous
@traveler adult
@not_passing draft

Narrative:
As a DOT com engineer
I want to add Byteman tests for trip management
so that I can verify that DB errors do not affect my purchase

Scenario: adult air purchase

Given I am flying a round-trip Southwest Southwest flight
And I am injecting a ./src/main/stories/fault_injection/btm/TripManagementStoreImpl_Save_HibernateException.btm fault
When I search and select a flight and am on the purchase page
And I complete the booking process adding the Air product to a new trip named MyTrip
Then I see that the Air product just booked has the name MyTrip on the Confirmation Page
And I unload all byteman rules
