Verify Add Rapid Rewards link in upcoming trip details page

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
I want to book a flight for 2 adults and verify Add Rapid Rewards Number link in upcoming trip details page

Scenario: User clicks the Add Rapid Rewards number link from upcoming trip details and it navigates to Add Rapid Rewards number page.


Given I am logged in as a goodUser Rapid Rewards member on the Search Flights page
And I am flying a round-trip Southwest Southwest flight
When I book a flight for 2 adults
And I click on the logout link
And I login as a goodUser Rapid Rewards member on the Search Flights page
And I click my account link
And I click to see details of my upcoming trip
And I click Add Rapid Rewards Number Link in the page
Then I should be in the Add Rapid Rewards Number page
And I see confirmation number and name pre populated in Add Rapid Rewards Number page