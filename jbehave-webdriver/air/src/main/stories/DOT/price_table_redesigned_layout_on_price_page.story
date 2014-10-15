Verify price table redesigned layout on price page
!-- counterpart to regression/price_table_standard_layout_on_price_page

Meta:
@project dot
@flow air
@process booking
@traveler adult
@storyId CHCO1188
Narrative:
In order to view the redesigned Pricing module,
As a customer
I want to search for a flight, select, and continue to the pricing page

Scenario: Viewing redesigned price table module on price page

Given I am flying a round-trip Southwest Southwest flight
And I am on the select flights page
When I select and view the Price page for a flight
Then the price table should have the revised column headers
And I should see fare type descriptions in the price rows
And the fare types should have matching descriptions
And individual fare type links in the fare type column open the fare rules in a new window
And I should not see the fare rules link in the price table footer
And I should see that the price table fare type column header has a link that opens the fare rules in a new window
