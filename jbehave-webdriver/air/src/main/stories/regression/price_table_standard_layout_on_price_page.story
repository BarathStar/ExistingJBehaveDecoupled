Verify price table standard layout on price page
!-- counterpart to DOT/price_table_redesigned_layout_on_price_page

Meta:
@project dot
@suite regression
@flow air
@process booking
@traveler adult
@storyId CHCO1188
@not_passing disableOldRegressionPriorToDOT

Narrative:
In order to view the redesigned Pricing module,
As a customer
I want to search for a flight, select, and continue to the pricing page

Scenario: Viewing redesigned price table on price page,

Given I am flying a round-trip Southwest Southwest flight
And I am on the select flights page
When I select and view the Price page for a flight
Then the price table should have the old column headers
And I should not see fare type descriptions in the price rows
And individual fare type links in the fare type column open the fare rules in a new window
And I should not see the fare rules link in the price table fare type column header
And I should see that the price table footer has a link that opens the fare rules in a new window
