Checked baggage policy and DOT full fare disclosure on select flights page

Meta:
@project dot
@flow air
@process booking
@user anonymous
@traveler adult
@storyId CHCO1160

Narrative:
In order to verify links on the select flights page
As a customer
I want to search for a round-trip flight

Scenario: View checked bags policy and DOT full fare disclosure links on select flight page

Given I am flying a round-trip Southwest Southwest flight
And I am on the select flights page
Then I should see a link that opens the checked baggage policy in a new window
And I should see a link that opens the full fare disclosure in a new window
