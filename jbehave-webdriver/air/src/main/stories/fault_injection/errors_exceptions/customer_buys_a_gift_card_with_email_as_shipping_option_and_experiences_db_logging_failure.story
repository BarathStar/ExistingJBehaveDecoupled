Buy a Gift Card, so that I can send the Gift Card to the recipient by Email with fault injection.

Meta:
@suite faultInjectionServiceTier
@flow other
@process email
@user anonymous

Narrative:
As an Anonymous User
I want to buy a southwest giftcard selecting E-Mail as shipping method
In order to have a southwest giftcard
and I should see my confirmation page even if giftcard logging to database failed

Scenario: User buys a giftcard with E-Mail as shipping method
Given I am on the Home page
And I am injecting a ./src/main/stories/fault_injection/btm/SVCMServer_DatabaseWriteException.btm fault
When I click on the southwestgiftcard link from the Global Nav Footer
And I click on the continue button on the Gift Card Landing page
And I fill out the create a card form with the email delivery selected
And I click on the continue button on the Create a Card page
And I fill in my credit card and Billing Information
And I confirm the giftCard purchase
Then I see the GiftCard Confirmation Page