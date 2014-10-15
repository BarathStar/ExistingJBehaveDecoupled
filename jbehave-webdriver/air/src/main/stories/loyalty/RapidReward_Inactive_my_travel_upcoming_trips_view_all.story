Inactive Rapid Rewards Member in upcoming trip details page

Meta:
@flow air
@user rr_member
@dyna_stubs
@storyId AV-2415, AV-2416
@project_in_dev
@project avengers_14.1

Narrative:
As an Inactive Rapid Rewards member, I want to view all of my upcoming trips under My Travel

Scenario: Inactive Rapid Reward member clicks upcoming trip and navigates to trip details page to verify the their upcoming Trips

Given I am a Rapid Rewards Inactive Member with an upcoming trip
When I view My Travel Upcoming Trips List
And I am on My Travel
And I click to see details of my upcoming trip
Then I see the Upcoming Trips Details Page

