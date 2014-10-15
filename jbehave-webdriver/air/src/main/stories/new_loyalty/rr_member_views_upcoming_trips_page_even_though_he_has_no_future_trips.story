Check the Upcoming Trips section, so that I reach "Upcoming Trips" page even though there no trips to be displayed.

Meta:
@flow rr
@process loyalty
@user rr_member
@not_live
@dyna_stubs

Narrative:
In order to see a message indicating that there are no Upcoming Trips and reach Upcoming Trips page
As a Rapid Rewards Member (without Upcoming Trips)
I want to log in and check my Upcoming Trips section

Scenario: Rapid Rewards Member views Upcoming Trips page even though he has no future trips
Given I am a Rapid Rewards Member without Upcoming Trips in my account
When I login as a Rapid Rewards Member from Login page
And I access my account's Travels
Then I see the My Travel Snapshot page
And I see a message indicating that there are no Upcoming Trips in My Travel Snapshot page
And I see the breadcrumb shown in the following order: 'My Account -> My Travel'
And I see that My Account is the only link in the breadcrumb
When I decide to check all the Upcoming Trips from my account's Travels
Then I see the Upcoming Trips page
And I see a message indicating that there are no Upcoming Trips in Upcoming Trips page