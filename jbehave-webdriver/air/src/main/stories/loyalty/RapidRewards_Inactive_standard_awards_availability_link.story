Standard Awards Availability Link for Inactive Rapid Rewards Member

Meta:
@flow rr
@process loyalty
@user rr_member
@dyna_stubs
@storyId AV-2443
@project_in_dev
@project avengers_14.1

Narrative:
As an Inactive Rapid Rewards member, I want to see Standard Awards Availability Link on Awards page

Scenario: Inactive Rapid Reward member clicks view credits and awards link and navigates to awards details page to verify the Standard Awards Availability Link

Given I am a Rapid Rewards Member with my account inactive
When I log in from the Account Login page
And I click view Old Awards and Credits
Then I see the Awards Details Page with Standard Awards Availability Link
