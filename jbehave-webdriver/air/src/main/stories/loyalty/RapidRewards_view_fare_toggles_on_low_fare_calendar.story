Low Fare Calendar Page should show dollar/points toggles during a dollar booking

Meta:
@flow air
@process booking
@user anonymous
@passenger adult
@dyna_stubs
@storyId AV-2277
@project_in_dev
@project avengers_14.1

Narrative:
As a user
I want to see the dollar/points toggles on the Low Fare Calendar Page during a dollar booking
So that I can switch fare types between dollar/points

Scenario: User booking on low fare calendar sees the dollar/points dollar toggles

Given I am flying a BusinessSelect round-trip Southwest Southwest flight departing today and returning tomorrow
And I am flying from DAL to HOU
When I search for my flight from the low fare calendar page
Then I should see the fare type toggle with dollars-points options
