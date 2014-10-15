Verify oops message when I try to purchase a giftcard so that I can know that I am not able to buy giftcards when services are down

Meta:
@flow other
@user anonymous
@dyna_stubs
@not_live

Narrative:
As an Anonymous User
I want to attempt to buy a giftcard and be in informed that I am not able to finish the purchase since the services are down
In order to be shown an oops message when I am trying to purchase a giftcard while services are down

Scenario: User is shown an oops message while attempting to purchase a giftcard with the services down
Given I am on the Gift Card Landing page and the services are down
When I click on the continue button on the Gift Card Landing page
And I fill out the create a card form with the email delivery selected
And I click on the continue button on the Create a Card page
And I fill in my credit card and Billing Information
And I confirm the giftCard purchase
Then I see an oops message which informs me that the purchase was not completed successfully