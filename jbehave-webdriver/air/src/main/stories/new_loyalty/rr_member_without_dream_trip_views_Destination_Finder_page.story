Verify all the places I can go on Southwest Airlines and browse estimated fares using Rapid Rewards points.

Meta:
@flow rr
@process loyalty
@user rr_member
@dyna_stubs

Narrative:
In order to see all the places I can go on Southwest Airlines and browse estimated fares using Rapid Rewards points
As a Rapid Rewards Member (without a dream trip setup)
I want to see "Destination Finder" page

Scenario: Rapid Rewards Member (without a dream trip setup) views "Destination Finder" page
Given I am a Rapid Rewards Member without a dream trip setup
When I log in from the account sidebar at the Search Flight page
And I expand My Rapid Rewards section
Then I see a message encouraging me to set up a dream trip
When I click the option to track my trip from the account sidebar
Then I see Destination Finder page
And I see the option to filter the departing cities
And I see the option to filter the destination cities
And I see the slider points range
And I see the points range section
And I see the destination finder map
