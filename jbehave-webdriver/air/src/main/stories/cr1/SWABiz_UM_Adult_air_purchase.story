SWABiz accompanied minor(5-11)

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@user swabiz_anonymous_um
@traveler UM
@dyna_stubs
@project_AccordionPage


Narrative:
In order to use SWABiz Anonymous air purchase
As a SWABiz Anonymous customer
I want to book a trip for an accompanied minor (5-11)

Scenario: SWABiz Anonymous air purchase for only FL segments

Given I am flying a round-trip AirTran AirTran flight
And I have anonymously logged into a SWABiz account
When I search for a flight for an adult and young traveler
And I select and purchase a flight
Then I should see the SWABiz confirmation page

