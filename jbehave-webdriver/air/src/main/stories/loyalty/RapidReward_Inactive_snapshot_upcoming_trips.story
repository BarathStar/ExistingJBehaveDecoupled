Inactive Rapid Rewards Member in upcoming trip snapshot

Meta:
@flow air
@user rr_member
@global_nav_regression
@dyna_stubs
@storyId AV-2345
@project_in_dev
@project avengers_14.1

Narrative:
As an Inactive Rapid Rewards member, I want to view all of my upcoming trips under Snapshot tab.

Scenario: Rapid Reward member clicks views Snapshots upcoming trips.

Given I am a Rapid Rewards Inactive Member with an upcoming trip
When I view Snapshot
Then I view my upcoming trips
