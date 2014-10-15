Verify a Gift Card purchase for someone else can be sent by Federal Express.

Meta:
@flow other
@user anonymous
@dyna_stubs

Narrative:
As an Anonymous User
I want to buy a southwest giftcard selecting Federal Express option
as a shipping method
In order to have a southwest giftcard

Scenario: User buys a giftcard with Federal Express as shipping method
Given I am on the Home page
When I click on the southwestgiftcard link from the Global Nav Footer
And I click on the continue button on the Gift Card Landing page
And I fill out the create a card form with the Federal Express delivery method selected
And I click on the continue button on the Create a Card page
And I fill in my credit card and Billing Information
And I confirm the giftCard purchase
Then I see the GiftCard Confirmation Page