Cancel flight as an adult

Meta:
@suite smoke
@flow air
@process cancel
@user anonymous
@traveler adult
@dyna_stubs
@project_AccordionPage

Narrative:
In order to cancel my boarding pass
As an adult
I want to cancel my flight

Scenario: adult air cancel

Given I search for a roundtrip air ticket from DAL to HOU
When I search and book a flight from search flights page
And I cancel the flight
Then I view the flight cancellation confirmation
