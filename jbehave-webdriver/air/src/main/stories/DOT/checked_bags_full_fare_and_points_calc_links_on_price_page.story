Checked bags policy, DOT full fare disclosure, and points calculation on price page

Meta:
@project dot
@flow air
@process booking
@user anonymous
@traveler adult
@storyId CHCO1173 CHCO1189
@not_passing draft

Narrative:
In order to Verify links on the price page
As a customer
I want to select a round-trip flight

Scenario: View checked bags policy, DOT full fare disclosure, and points calculation links on price Page

Given I am flying a round-trip Southwest Southwest flight
And I am logged in as a AlistPreferred Rapid Rewards member on the Search Flights page
When I am booking a round-trip flight
And I select flights and continue
Then I should see a link that opens the checked baggage policy in a new window
And I should see a link in the price table that opens the points calculation in a popup
