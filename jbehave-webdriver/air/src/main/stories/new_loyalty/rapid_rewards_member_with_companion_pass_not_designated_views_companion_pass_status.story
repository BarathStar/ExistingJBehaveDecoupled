Complete all the companion information.

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs
@not_live

Narrative:
In order to be able to complete all the Companion Pass information
As a Rapid Rewards Member (with Companion Pass status achieved)
I want to log in and reach Companion Pass Status page accessing through the Companion Pass section on the account sidebar

Scenario: Rapid Rewards Member (with Companion Pass status achieved) views Companion Pass Status page
accessing through the Companion Pass section on the account sidebar
Given I am a Rapid Rewards Member with Companion Pass status achieved without a designated Companion Pass
When I log in from the account sidebar at the Search Flights page
And I expand My Rapid Rewards section
Then I see the Companion Pass status Achieved on the account sidebar
When I access the Companion Pass Status page through the Companion Pass section on the account sidebar
Then I see the Companion Pass Status page
And I see the breadcrumb shown in the following order: My Account -> My Rapid Rewards -> Companion Status