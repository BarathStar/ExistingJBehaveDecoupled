Low Fare Calendar Page should show dollar/points/certificate toggles during award booking

Meta:
@flow air
@process booking
@user rr_member
@passenger adult
@dyna_stubs
@storyId AV-2277
@project_in_dev
@project avengers_14.1

Narrative:
As a Rapid Rewards member
I want to see the dollar/points/certificate toggles on the Low Fare Calendar Page during an award booking
So that I can switch fare types between dollar/points/certificate

Scenario: Rapid rewards member in award booking sees the dollar/points/certificate toggles

Given I am a Rapid Rewards member with both a Freedom and Standard Award
And I am flying a BusinessSelect round-trip Southwest Southwest flight departing today and returning tomorrow
And I am flying from DAL to HOU
And I am logged in as Rapid Rewards member
When I select to see my old awards
And I attempt to book an awards flight with flexible dates
Then I should see the fare type toggle with dollars-points-certificates options
