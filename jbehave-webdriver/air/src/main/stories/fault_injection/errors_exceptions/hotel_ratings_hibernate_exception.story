Hotel ratings exception on price page

Meta:
@flow air
@process booking
@suite faultInjectionServiceTier
@user anonymous
@traveler adult
@not_passing draft

Narrative:
As a southwest.com customer
Given I pick flights on the select flight page
And a DB outage occurs
When I click to continue
Then I can successfully make a booking

Scenario: adult air purchase with hotel ratings db exception

Given I search for a roundtrip air ticket from DAL to HOU
And I am injecting a ./src/main/stories/fault_injection/btm/HotelRatingStore_HibernateException.btm fault
When I search and book a flight from search flights page
Then I receive an air confirmation number
And I unload all byteman rules
