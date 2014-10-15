Search on select flights page as an adult, if the outbound/inbound flights have not been selected an oops message should be displayed.

Meta:
@flow air
@process booking
@user anonymous
@passenger adult
@dyna_stubs

Narrative:
As an Adult
I want to search on select flights page and don't select outbound/inbound flights
So that

Scenario: Verify that a Oops message appear when the customer don't select the outbound/inbound flights

Given I am flying a round-trip Southwest Southwest flight
When I successfully search for flights from the flight search page
And I select flights and continue
And I come back to select flight
And I select 4 day(s) more for return flight
And I click continue to the Price page
Then I should see a Oops message indicating that no flight has been selected