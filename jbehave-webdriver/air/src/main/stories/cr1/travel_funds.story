Travel Funds

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@storyId DCAIR4874
@message Data driven, drafting until data issue fixed
@not_passing draft



Narrative:  In order to use travel funds
As a user
I want to see my travel funds applied

Scenario: Verify AirTran messaging appears on Travel Funds section

Given I am flying a WannaGetAway round-trip AirTran AirTran flight
When I book a flight
And I select to change my entire itinerary from the confirmation page
And I upgrade to Anytime fare and reach the reconcile page
Then I should view AirTran messaging on the Reconcile page


Scenario: Verify airtran message on view travel funds page

Given I am on the Travel Funds Page
Then I see the AirTran message
