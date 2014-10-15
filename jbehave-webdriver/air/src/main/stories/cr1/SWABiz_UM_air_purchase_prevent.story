SWABiz Oops Message for an unattended minor(5-11)

Meta:
@flow air
@process booking
@user swabiz_anonymous
@traveler adult and UM
@dyna_stubs
@project cr1
@airTranOnly
@project_AccordionPage

Narrative:
In order to use SWABiz Anonymous air purchase
As a SWABiz Anonymous customer
I want to see a Oops Message when I try to book a trip for an unaccompanied minor (5-11)

Scenario: SWABiz Anonymous air purchase for only FL segments

Given I am flying a round-trip AirTran AirTran flight
And I have anonymously logged into a SWABiz account
When I search for a flight for an unaccompanied child
And I select and attempt to purchase a flight
Then I see the oops message

