Log in to Rapid Rewards Member held account and see a warning message specifying that my account is temporarily disabled.

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs
@not_live

Narrative:
As a Rapid Rewards Member (with a held account)
I want to login and check My Rapid Rewards section in the account sidebar
In order to be informed that my account is temporarily disabled

Scenario: Rapid Rewards Member (with a held account) views a warning message specifying that his account is temporarily disabled
Given I am a Rapid Rewards Member with my account held
When I log in from the account sidebar at the Search Flight page
And I expand My Rapid Rewards section
Then I see a warning message alerting me of my account status