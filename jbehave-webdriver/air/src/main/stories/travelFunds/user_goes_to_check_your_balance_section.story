User goes to Check your Balance Section

Meta:
@flow travel extras
@process view
@suite regression
@travelextras
@user anonymous
@storyId SWACOMTT-1004
@not_passing draft
@message This story REQUIRES that the CAPTCHA off toggle be used (-DCAPTCHA_Off), this may require changes to jobs run on Jenkins

Narrative:
In order to validate the Check your Balance option for a giftcard
As a user
I want to be redirected to the Check your Balance section and try to retrieve a giftcard with invalid information

Scenario: User tries to retrieve a giftcard with invalid information

Given I am on the Home page
When I click on the southwestgiftcard Balance link on the Home Page
Then I see the check your balance tab selected by default on the giftcard landing page
And I see the Giftcard section with the link Check another southwestgifcard  and the verbiage (up to 4)
When I enter one Giftcard which does not exist
And I click on the check now button
Then I see the Oops Messages related to the non-existent giftcard
When I enter one Invalid Card Number and Security Code
And I click on the check now button
Then I see the Oops Messages related to the invalid card length and invalid security code length