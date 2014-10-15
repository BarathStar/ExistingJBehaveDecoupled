Click the create cards breadcrumb link in verify & pay page

Meta:
@flow other
@user anonymous
@dyna_stubs

Narrative:
As a user to purchase the giftcard
I want to click the create cards breadcrumb link on the verify & pay page
So that I can go back and change the information I put in earlier

Scenario: See the information entered in the create cards page get populated in the verify & pay page
Given I am on the Gift Card Landing page
When I click on the continue button on the Gift Card Landing page
And I enter the required information including quantity 1 in the Gift Card create cards page and proceed
Then I see the quantity 1 in the Gift Card purchase page
When I click the create cards breadcrumb link in the Gift Card purchase page
And I enter the quantity 2 in the Gift Card create cards page and proceed
Then I see the quantity 2 in the Gift Card purchase page
