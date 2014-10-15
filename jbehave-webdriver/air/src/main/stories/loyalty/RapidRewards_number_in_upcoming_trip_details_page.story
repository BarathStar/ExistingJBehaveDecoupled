Rapid Rewards number in upcoming trip details page

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs
@project avengers_13.6
@project_in_dev
@storyid AV-1094

Narrative:
As a Rapid Rewards Member
I want to see the Rapid Rewards number in upcoming trip details page

Scenario: Rapid Reward member clicks upcoming trip and navigates to trip details page to verifies the Rapid Rewards Number

Given I am a Rapid Rewards Member with an upcoming trip
And I am logged in as a Rapid Rewards Member
When I click to see details of my upcoming trip
Then I check the Rapid Rewards Number in the page
