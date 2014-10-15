Verify an error message specifying that it is not possible to retrieve Promotional Certificates due to a CLCS Certificates service outage.

Meta:
@flow rr
@process loyalty
@user rr_member
@dyna_stubs
@not_live

Narrative:
In order to see an error message specifying that it is not possible to retrieve Promotional Certificates
As a Rapid Rewards Member (with Promotions and Promotional Certificates)
I want to reach "Promotions" page (accessing through My Rapid Rewards section) when there is a CLCS Certificates service outage

Scenario: Rapid Rewards Member (with Promotions and Promotional Certificates) views an error message in "Promotions" page (accessing through My Rapid Rewards section) when there is a CLCS Certificates service outage
Given I am a Rapid Rewards Member with Promotions and Promotional Certificates
And CLCS Certificates service is unavailable but CLCS Promotions service is available
When I login as a Rapid Rewards Member from Login page
And I access my account's Rapid Rewards
And I access my account's Promotions from My Rapid Rewards page
Then I see Rapid Rewards Promotions page
And I see a message indicating that Promotional Certificates can not be retrieved on Promotions page
And I should not see a message indicating that Promotions can not be retrieved on Promotions page