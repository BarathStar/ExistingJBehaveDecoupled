Verify two error messages specifying that it is not possible to retrieve Promotional Certificates and Promotions. (Messages are not shown as the CLCS Promotions and Certificates services are fully available)

Meta:
@flow rr
@process loyalty
@user rr_member
@dyna_stubs
@not_live
@project Loyalty Marketing_Pages
@storyId SWACOMTT-1593
@suite regression
@user rapid rewards member

Narrative:
In order to not see error messages specifying that it is not possible to retrieve Promotions and Promotional Certificates
As a Rapid Rewards Member (with Promotions and Promotional Certificates)
I want to reach "Promotions" page (accessing through My Rapid Rewards section) when there is no CLCS Promotions and Certificates services outage

Scenario: Rapid Rewards Member (with Promotions and Promotional Certificates) does not view error messages in "Promotions" page (accessing through My Rapid Rewards section) as there is no CLCS Promotions and Certificates services outage
Given I am a Rapid Rewards Member with services available for Promotions and Promotional Certificates
When I login as a Rapid Rewards Member from Login page
And I access my account's Rapid Rewards
And I access my account's Promotions from My Rapid Rewards page
Then I should not see a message indicating that Promotional Certificates can not be retrieved on Promotions page
And I should not see a message indicating that Promotions can not be retrieved on Promotions page