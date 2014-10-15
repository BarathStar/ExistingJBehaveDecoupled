Verify price table redesigned layout on confirmation page
!-- counterpart to regression/price_table_standard_layout_on_confirmation_page

Meta:
@project dot
@flow air
@process booking
@traveler adult
@storyId CHCO1182

Narrative:
In order to view the redesigned Pricing module,
As a customer
I want to search for a flight, select, complete purchasing, and continue to the confirmation page

Scenario: Viewing redesigned price table module on confirmation page

Given I am flying a round-trip Southwest Southwest flight
When I book a flight
Then I should see that the price table on the confirmation page has the revised column headers
And I should see fare type descriptions in the price table rows on the confirmation page
And the fare types on the confirmation page should have matching descriptions
And individual fare type links in the fare type column on the confirmation page open the fare rules in a new window
And I should not see a fare rules link in the price table footer on the confirmation page
And the price table fare type column header on the confirmation page should have a link that opens the fare rules in a new window
