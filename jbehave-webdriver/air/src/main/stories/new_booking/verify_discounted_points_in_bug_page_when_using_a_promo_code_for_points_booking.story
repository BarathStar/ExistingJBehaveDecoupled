Verify that points are discounted in the select flight page when using a Promo Code that applies to points
NOTE: This story will be updated to include more verification when the discounts can be verified on screen.

Meta:
@flow air
@process booking
@user anonymous
@passenger adult
@dyna_stubs
@project avengers_13.10
@project_in_dev
@not_live

Narrative:
As an adult
I want to enter a promo code that applies to points purchases
So that I can see discounted points fare and use points for a discounted purchase

Scenario: Verify the discounted points are seen in the select flights page

Given I am using Promo Code SWAPOINTS
And I am on the Select Flight page
And I expand the Search Widget by selecting Additional Search Options
And I enter SWAPOINTS into the Promo Code field
Then I should see blue informational alert message
When I click on Points option
Then I do not see an oops message on the Select Flights Page
And I see strike through for original redemption points
