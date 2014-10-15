Search using booking widget within my southwest


Meta:
@project cr1
@flow air
@process booking
@storyId DCQA29
@not_passing draft

Narrative: In order to search using booking widget within my southwest
            As a Rapid Rewards Member
            I want to book a flight
            so that I can use the booking widget

Scenario: Verify Book a flight modal

Given I am flying a one-way Southwest flight
And I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I select book a flight
Then I see the book a flight modal
When I close the book a flight modal
And I select My Travel link
And I select book a flight
And I select My Rapid Rewards link
And I select book a flight

Scenario: Booking widget senior check

Given I am flying a one-way Southwest flight
And I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I select book a flight
Then I see the book a flight modal
When I select fare in dollars
And I book a flight as a senior and search from Rapid Rewards
Then I see the senior fares displayed
And I do not see the points toggle


Scenario: Booking widget senior field check

Given I am flying a one-way Southwest flight
And I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I select book a flight
Then I see the book a flight modal
When I enter departure and arrival stations
Then I should see the senior passenger count as 0

