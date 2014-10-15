Display Dollar/Points fare toggle after switch to dollar/points booking during awards booking

Meta:
@flow air
@process booking
@user rr_member
@passenger adult
@dyna_stubs
@storyId AV-2250
@project_in_dev
@project avengers_13.11

Narrative:
As a Rapid Rewards member
I want to switch to dollar/points booking from incomplete award booking
So that I can book a flight with dollar/points

Scenario: Rapid rewards member in awards booking decides to book flight with dollar/points booking

Given I am a Rapid Rewards member with both a Freedom and Standard Award
And I am flying a round-trip Southwest Southwest flight
And I am logged in as Rapid Rewards member
When I select to see my old awards
And I attempt to book an awards flight
And I see dollar-points-certificate toggle
And I attempt to search for flights from the flight search page
Then I should see dollar-points toggle
