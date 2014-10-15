Transfer Rapid Rewards Points via Points.com

Meta:
@suite pointsDotCom
@flow out_of_path
@process loyalty
@user rr_member
@traveler adult
@project points_dot_com
@POINTSDOTCOM_BUY_GIFT_TRANSFER toggle is ON
@dyna_stubs

Narrative:
As a Rapid Rewards Member
I want to access the Points.com page

Scenario: Rapid Rewards Member navigates from Snapshot Page to Buy, Gift & Transfer Points page to points.com iframe container page via Transfer button
Given I am logged in as Rapid Rewards member
When I click on Buy, Gift & Transfer Points
And I click on the Transfer button
Then I should see the points.com Transfer page in the iframe
