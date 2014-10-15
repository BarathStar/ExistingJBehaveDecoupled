Rewards Transfer A+ Login with RR member that has more than three transferable Awards and Certificates

Meta:
@suite rewardsTransfer
@flow awards
@process exchange
@traveler customer
@storyId ZR206
!--storyId ZR207
@not_passing pending
@dyna_stubs


!--This story requires special RR account

Narrative:
As a rapid rewards member
I want to navigate to the AirTran A+ login screen from multiple points
So I can access the rewards transfer login page and view my transferable Awards

Scenario: Login to Rewards Transfer Page and view transferable Awards with RR account that has more than three awards or certificates

Given I am on the Rewards Transfer page
Then I view my transferable Awards and Certificates
And I view my transferable Credits
And I view my transferable Points
And I view the See More Awards link
And I see the first award is selected and highlighted

When I click on the See More Awards link
Then I should see the See Fewer Awards link
And I should not see the See More Awards link

When I select the fourth transferable Certificate
Then I should see the AirTran transfer rate in the credit info section
And I should see the Airtran transfer rate in the confirmation section
And I should not see the See More Awards link
And I should not see the See Fewer Awards link
