Checked baggage policy and DOT full fare disclosure on SWABIZ select flights page

Meta:
@project dot
@flow air
@process booking
@user anonymous
@traveler adult
@storyId CHCO1160

Narrative:
In order to verify links on the SWABIZ select flight page
As a customer
I want to search for a round-trip flight

Scenario: View checked baggage policy and DOT full fare disclosure links on SWABIZ select flights page

Given I am flying a round-trip Southwest Southwest flight
And I have anonymously logged into a SWABiz account
And I am on the select flights page
When I select flights and continue
Then I should see a link that opens the checked baggage policy in a new window

