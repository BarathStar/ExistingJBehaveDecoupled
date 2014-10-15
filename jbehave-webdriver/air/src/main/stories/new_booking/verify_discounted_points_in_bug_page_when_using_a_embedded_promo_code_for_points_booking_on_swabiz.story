Verify that points are discounted in the select flight page when an Embedded Promo Code is active
NOTE: This story will be updated to include more verification when the discounts can be verified on screen.

Meta:
@flow air
@process booking
@user anonymous
@passenger adult
@dyna_stubs
@project avengers_14.1
@project_in_dev
@not_live

Narrative:
As an adult
I want to make points purchases on Swabiz when Embedded Promo Code is active for my company id.
So that I can see discounted points fare and use points for a discounted purchase

Scenario: Verify the discounted points are seen in the select flights page

Given I work for a SwaBiz company 99067846
And I have anonymously logged into a SWABiz account
And I am on the Select Flight page on SWABiz
When I click on Points option
Then I see strike through for original redemption points
And I do not see an oops message on the Select Flights Page



