Verify that points are discounted in the select flight page when a Website Wide Promo is active on dotcom

Meta:
@flow air
@process booking
@user anonymous
@passenger adult
@dyna_stubs
@project avengers_14.2
@project_in_dev
@not_live

Narrative:
As an adult
I want make a points purchase when Website Wide Promo is active on dotcom
So that I can see discounted points fare and use points for a discounted purchase

Scenario: Verify the discounted points are seen in the select flights page for Website Wide Promo

Given Website wide promo WEBSITEWIDE is enabled
And I am on the Select Flight page
When I click on Points option
Then I see strike through for original redemption points
And I do not see an oops message on the Select Flights Page
