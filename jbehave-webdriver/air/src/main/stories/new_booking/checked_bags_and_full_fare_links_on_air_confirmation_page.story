Checked baggage policy link on confirmation page with air purchase

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@project dot

Narrative:
In order to verify baggage policy links on the air confirmation page
As a customer
I want to search and select flights, complete purchase, and continue to the confirmation page

Scenario: Viewing the checked baggage policy link on the confirmation page

Given I am flying a round-trip Southwest Southwest flight
And I am on the confirmation page
Then I should see a link that opens the checked baggage policy in a new window
And I should see a link that opens the full fare disclosure in a new window
