Verify two error messages specifying that is not possible to retrieve Promotional Certificates and Promotions due to the CLCS Promotions and Certificates services outage.

Meta:
@flow rr
@process loyalty
@user rr_member
@not_live
@dyna_stubs

Narrative:
In order to see an error message specifying that it is not possible to retrieve Promotions and Promotional Certificates
As a Rapid Rewards Member (with Promotions and Promotional Certificates)
I want to reach "My Rapid Rewards" account section when there is a CLCS Promotions and Certificates services outage

Scenario: Rapid Rewards Member (with Promotions and Promotional Certificates) views error messages in My Rapid Rewards account section when there is a CLCS Promotions and Certificates services outage
Given I am a Rapid Rewards Member with Promotions and Promotional Certificates
And CLCS Promotions and Certificates services are unavailable
When I login as a Rapid Rewards Member from Login page
And I access my account's Rapid Rewards
Then I see a message indicating that Promotional Certificates can not be retrieved on My Rapid Rewards Account page
And I see a message indicating that Promotions can not be retrieved on My Rapid Rewards Account page