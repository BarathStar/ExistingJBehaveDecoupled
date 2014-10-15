Upsell and welcome drawer on select flights page

Meta:
@flow air
@process booking
@user anonymous
@traveler adult

Narrative:
In order to see the upsell drawer buttons
As an adult
I want to select origin and destinations on the search page and continue to the select flight page

Scenario: Select flights eligible for upsell and click buttons in upsell drawers
Given I am flying a round-trip Southwest Southwest flight
And I am a customer on the select flights page traveling from DAL to AUS
Then I should see that the outbound upsell drawer is closed
When I select an outbound Anytime flight that qualifies for upgrade to Business Select
Then I should see that the outbound upsell drawer is opened
And I should see a text link for No thanks, keep Anytime
When I click the outbound upsell drawer no thanks text link
Then I should see that the outbound upsell drawer is closed
When I select an outbound Anytime flight that qualifies for upgrade to Business Select
Then I should see that the outbound upsell drawer is opened
When I click the outbound upsell drawer close button
Then I should see that the outbound upsell drawer is closed
When I select an outbound Anytime flight that qualifies for upgrade to Business Select
Then I should see that the outbound upsell drawer is opened
When I click the upgrade button in the outbound Anytime upsell drawer
Then I should see the welcome drawer for Business Select
And I should see the return flights button displayed in the welcome drawer
When I click the outbound upsell drawer close button
Then I should see that the outbound upsell drawer is closed