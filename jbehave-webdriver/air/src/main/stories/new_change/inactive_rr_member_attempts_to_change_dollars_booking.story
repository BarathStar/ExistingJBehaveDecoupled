Verify that an inactive Rapid Rewards member can change a future dollars booking

Meta:
@dyna_stubs
@flow air
@process change
@user rr_member
@passenger adult
@traveler adult
@project avengers_14.1
@project_in_dev
@storyid AV-2403

Narrative:
As a logged-in inactive Rapid Rewards Member
In order to verify changing a booked itinerary
I want to see the Itinerary Changed page

Scenario: Verify that an inactive Rapid Rewards member can change a future dollars booking

Given I am flying a Anytime round-trip Southwest Southwest flight departing tomorrow and returning day-after
And I am a Rapid Rewards Member with my account inactive
And I have booked this flight
When I log in from the account sidebar at the Search Flight page
And I change the flight to a later date
Then I should see the itinerary change confirmation page