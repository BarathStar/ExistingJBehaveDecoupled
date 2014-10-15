verify when a customer attempts to buy a Gift Card with a rejected credit card and see an oops as an user

Meta:
@flow other
@process giftcard
@user anonymous
@passenger adult
@dyna_stubs
@not_live

Narrative:
As an Anonymous User
I want to buy a southwest giftcard with a rejected credit card
In order to have a southwest giftcard

Scenario: User buys a giftcard with a rejected credit card

Given I am on the Home page
And I have my credit card VISA with number 4539 0820 3939 6288 and security code 135 rejected from the bank
When I click on the southwestgiftcard link from the Global Nav Footer
And I click on the continue button on the Gift Card Landing page
And I fill out the create a card form with the email delivery selected
And I click on the continue button on the Create a Card page
And I fill in my credit card and Billing Information
And I confirm the giftCard purchase
Then I see an oops with the message: We are unable to process your transaction using the credit card provided