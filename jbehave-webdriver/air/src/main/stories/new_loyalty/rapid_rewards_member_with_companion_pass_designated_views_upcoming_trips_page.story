View future trips.

Meta:
@flow rr
@process loyalty
@suite regression
@user rr_member
@not_live
@dyna_stubs

Narrative:
In order to see my Upcoming Trips
As a Rapid Rewards Member with a designated Companion Pass
I want to log in and check my Upcoming Trips accessing through the Companion Pass section on the account sidebar

Scenario: Rapid Rewards Member with a Companion Pass designated views Upcoming Trips page
accessing through the Companion Pass section on the account sidebar
Given I am a Rapid Rewards Member with Companion Pass status achieved
And I have designated my Companion Pass
When I log in from the account sidebar at the Search Flights page
And I expand My Rapid Rewards section
Then I see the Companion Pass details on the account sidebar
When I access the Upcoming Trips through the Companion Pass section on the account sidebar
Then I see the Upcoming Trips page
And I see the breadcrumb shown in the following order: 'My Account -> My Travel -> Upcoming Trips'