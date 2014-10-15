Buy a Gift Card, so that I see the corresponding error messages when I do not complete the mandatory (required) fields.

Meta:
@flow other
@user anonymous
@dyna_stubs
@not_live

Narrative:
In order to verify the required information while attempting to buy a giftcard
As an Anonymous User
I want to avoid entering the mandatory fields and be informed that the required information has to be indicated in order to complete the giftcard purchase

Scenario: User does not enter mandatory fields while buying a giftcard
Given I am on the Home page
When I click on the southwestgiftcard link from the Global Nav Footer
And I click on the continue button on the Gift Card Landing page
And I click on the continue button on the Create a Card page
Then I see the Oops messages about the mandatory fields for the email delivery method
When I select the usps delivery method on the Create a Card page
And I click on the continue button on the Create a Card page
Then I see the Oops messages about the mandatory fields for the usps delivery method
When I select the fedex delivery method on the Create a Card page
And I click on the continue button on the Create a Card page
Then I see the Oops messages about the mandatory fields for the federal express delivery method
When I fill out the create a card form with the email delivery selected
And I click on the continue button on the Create a Card page
And I click on the purchase button on the Purchase a Card page
Then I see the Oops messages about the mandatory fields on the Purchase a Card page