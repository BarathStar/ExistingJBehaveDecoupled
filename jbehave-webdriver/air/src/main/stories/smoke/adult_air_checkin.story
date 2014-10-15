Adult Air Checkin

Meta:
@suite smoke
@flow air
@process checkin
@user anonymous
@traveler adult
@dyna_stubs
@project_AccordionPage

Narrative:
In order to receive my boarding pass
As an adult
I want to checkin for a flight


Scenario: Adult Checkin

Given I search for a roundtrip air ticket from DAL to HOU
When I book a flight eligible for checkin 1 adult
And I click on the Check in button on the Confirmation Page
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view my boarding pass
