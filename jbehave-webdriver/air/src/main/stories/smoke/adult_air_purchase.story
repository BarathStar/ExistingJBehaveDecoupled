Purchase an Adult Air Ticket

Meta:
@suite smoke
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@project_AccordionPage

Narrative:
In order to fly on a date that I want
As an adult
I want to book a flight and
receive a confirmation number

Scenario: adult air purchase

Given I search for a roundtrip air ticket from DAL to HOU
When I search and book a flight from search flights page
Then I receive an air confirmation number
