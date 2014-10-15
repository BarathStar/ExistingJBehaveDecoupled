Verify Web Referral Updated Modal Wording

Meta:
@suite regression
@flow air
@traveler adult
@storyId DCQA215
@project webreferral
@dyna_stubs
@not_passing service issues on the home page only
@owner Bash-Ahmed

Narrative:
In order to see international travel policies
As a customer
I want to search for a international flight from the homepage


Scenario: Add a car to a named trip that only has a flight

Given I am flying a round-trip international flight
When I am searching flights from search flight page
And I view the Web Referral Redirect Modal for One-way
And I click to continue from the AirTran Redirect Modal
Then I should see the AirTran search results page

