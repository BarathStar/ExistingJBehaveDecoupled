Passenger with infant Southwest only check in

Meta:
@flow air
@project checkin
@process checkin
@user anonymous
@traveler adult
@dyna_stubs
@storyId ZR-971

Narrative:
In order to receive a correct Boarding Pass
As an anonymous adult with an infant
I want to check in online

Scenario:  An anonymous adult with infant sees IN on Southwest Boarding Pass

Given I have a Southwest one-way reservation for an adult with an infant that is eligible for check in
When I check in from the check in page
And I click check in to view my boarding pass
Then I see IN in the special conditions section of the boarding pass
