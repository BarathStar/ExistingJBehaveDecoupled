Verify an error message specifying that it is not possible to retrieve Promotions due to the CLCS Promotions service outage.

Meta:
@flow rr
@process loyalty
@user rr_member
@dyna_stubs
@not_live


Narrative:
In order to see an error message specifying that it is not possible to retrieve Promotions
As a Rapid Rewards Member (with Promotions and no Promotional Certificates)
I want to reach "Promotions" page (accessing through My Rapid Rewards section) when there is a CLCS Promotions service outage

Scenario: Rapid Rewards Member (with Promotions and no Promotional Certificates) views an error message in "Promotions" page (accessing through My Rapid Rewards section) when there is a CLCS Promotions service outage
Given I am a Rapid Rewards Member with Promotions and no Promotional Certificates
When I login as a Rapid Rewards Member from Login page
And CLCS Promotions service is unavailable but CLCS Certificates service is available
And I access my account's Rapid Rewards
And I access my account's Promotions Page
Then I should not see a message indicating that Promotional Certificates can not be retrieved on Promotions page
And I see a message indicating that Promotions can not be retrieved on Promotions page