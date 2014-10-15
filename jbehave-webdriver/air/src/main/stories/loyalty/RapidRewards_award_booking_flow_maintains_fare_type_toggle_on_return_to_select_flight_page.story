Display Dollar/Points/Certificate fare toggle after returning to the Select Flight Page during awards booking

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
I want to see the dollar/points/certificate toggle upon returning to the Select Flight Page
So that I can book a flight with dollar/points/certificate

Scenario: Rapid rewards member in awards booking decides to book flight with dollar/points and returns to Select Flight Page

Given I am a Rapid Rewards member with both a Freedom and Standard Award
And I am flying a round-trip Southwest Southwest flight
And I am logged in as Rapid Rewards member
When I select to see my old awards
And I attempt to book an awards flight
And I toggle fare type to dollars
And I select and view the Price page for a flight
And I come back to select flight
Then I should see dollar-points-certificate toggle
