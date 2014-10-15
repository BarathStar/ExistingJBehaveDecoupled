Attempt to Buy Rapid Rewards Points via Points.com as a Non-RR Member

Meta:
@suite pointsDotCom
@flow out_of_path
@process loyalty
@user rr_customer
@traveler adult
@project points_dot_com
@POINTSDOTCOM_BUY_GIFT_TRANSFER toggle is ON
@dyna_stubs

Narrative:
As a non Rapid Rewards Member
I attempt to access the Points.com page

Scenario: Non Rapid Rewards Member navigates from Snapshot Page to Buy, Gift & Transfer Points page to points.com iframe container page via Buy button
Given I am logged in as Rapid Rewards customer (not a Rapid Rewards member)
When I go to the Rapid Rewards Overview Page
And I click on About tab
And I click on the Buy, Gift & Transfer button
And I click on the Buy button
Then I should see the modal urging me to upgrade my membership to Rapid Rewards
