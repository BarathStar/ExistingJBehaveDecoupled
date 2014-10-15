Verify price table standard layout on confirmation page
!-- counterpart to DOT/price_table_redesigned_layout_on_confirmation_page

Meta:
@project dot
@suite regression
@flow air
@process booking
@traveler adult
@storyId CHCO1182
@not_passing disableOldRegressionPriorToDOT

Narrative:
As a southwest.com user, I want to view the redesigned Pricing module
of the Price page within the booking path.

In order to view the redesigned Pricing module,
As a customer
I want to search for a flight, select, and continue to the pricing page

Scenario: Viewing redesigned price table on price page,

Given I am flying a round-trip Southwest Southwest flight
When I book a flight
Then I should see that the price table on the confirmation page has the old column headers
And I should not see fare type descriptions in the price table rows on the confirmation page
And individual fare type links in the fare type column on the confirmation page open the fare rules in a new window
And I should not see a fare rules link in the price table fare type column header on the confirmation page
And the price table footer on the confirmation page has a link that opens the fare rules in a new window
