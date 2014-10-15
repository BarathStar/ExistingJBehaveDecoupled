Travel Funds

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@user Anonymous
@traveler adult
@storyId DCAIR4874
@dyna_stubs
@project_in_dev

Narrative:  As a user
I want to see the error message when incomplete PNR applied in travel fund section on purchase page for codeshare flights
so that the travel fund has been validated

Scenario: Error message displays when user enters incomplete PNR in travel fund section

Given I am flying a round-trip AirTran SouthwestCodeshare flight
And I am on the purchase page
When I enter an incomplete confirmation number as a travel fund
Then I see the incomplete confirmation number error message
