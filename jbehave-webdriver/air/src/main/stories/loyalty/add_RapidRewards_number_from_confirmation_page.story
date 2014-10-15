Verify Add Rapid Rewards link in confirmation page

Meta:
@flow rr
@process loyalty
@user anonymous
@traveler adult
@dyna_stubs
@project avengers_13.6
@project_in_dev
@storyid AV-1094

Narrative:
As an anonymous user
I want to book a flight and verify Add Rapid Rewards Number link in confirmation page

Scenario: User clicks the Add Rapid Rewards number link from confirmation page and it navigates to Add Rapid Rewards number page.

Given I am flying a round-trip Southwest Southwest flight
When I book a flight for 1 adults
And I click Add Rapid Rewards Number Link in the confirmation page
Then I should be in the Add Rapid Rewards Number page
And I see confirmation number and name pre populated in Add Rapid Rewards Number page
