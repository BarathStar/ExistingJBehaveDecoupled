Buy a Gift Card and enter incorrect or incomplete information

Meta:
@flow other
@user anonymous
@dyna_stubs

Narrative:
As an Anonymous User
I want to enter invalid information while buying a giftcard and be notified that the provided infomation is not correct
In order to verify invalid information while attempting to buy a giftcard

Scenario: User provides invalid information while trying to purchase a giftcard
Given I am on the Home page
When I click on the southwestgiftcard link from the Global Nav Footer
And I click on the continue button on the Gift Card Landing page
And I fill out the create a card form with the email delivery selected
And I click on the continue button on the Create a Card page
And I fill in my credit card and Billing Information with invalid character(s) and e-mails that do not match
And I confirm the giftCard purchase
Then I see oops messages indicating that my information is not valid
When I fill in my credit card and Billing Information with valid information
And I confirm the giftCard purchase
Then I see that my purchase has successfully been finished on the Giftcard Purchase Page