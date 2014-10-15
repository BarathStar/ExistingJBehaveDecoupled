Verify that points are discounted on the fare breakdown flyout on the price page when using a Promo Code that applies to points

Meta:
@flow air
@process booking
@user anonymous
@passenger adult
@dyna_stubs
@project avengers_13.10
@project_in_dev
@storyId AV-2328

Narrative:
As an adult
I want to enter a promo code that applies to points purchases
So that I can see discounted points fare on the fare breakdown flyout on the price page during a points booking

Scenario: Verify the discounted points are seen on the fare breakdown flyout on the price page

Given I am flying a round-trip Southwest Southwest flight using promo code SWAPOINTS
And I am using Promo Code SWAPOINTS
When I am on the price page booking with Points
Then I see a discounted points fare on the fare breakdown flyout
