Verify that A list faq page shown a proper message

Meta:
@dyna_stubs
@flow air
@not_live
@passenger adult
@process view
@user anonymous

Narrative:
As an adult
In order to do a EarlyBird checkin, I want to verify that a message shown by A list page is correctq
So that

Scenario: Verify that A list faq page shown a proper message

Given I am flying a round-trip Southwest Southwest flight
When I have a flight booked
And I am on the early bird page
And I retrieve itinerary on the Early Bird check-in page
Then I am taken to the EB direct purchase path
When I click on A-List link on EarlyBird page
Then I should see the A list FAQ message
