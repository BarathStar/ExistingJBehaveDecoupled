Verify CC still selected after making a purchase for child under 5 years old at payment attempt

Meta:
@flow air
@process booking
@user rr_member
@passenger UM
@dyna_stubs
@not_live
@project_in_dev
@project SWAT

Narrative:
As an adult
I try book a flight for a child more 5 years old and I want to see preview Credit Card selected
So that

Scenario: Verify Credit Card still selected after making a purchase for child more 5 years

Given I am a Rapid Rewards Member
And I login from Login page
And I have stored 1 credit cards
And I have booked a round-trip SouthwestUMRoute SouthwestUMRoute flight for an adult
And I am flying a round-trip SouthwestUMRoute SouthwestUMRoute flight
And I am a minor traveling alone
When I attempt to purchase a flight as a minor
And I confirm there will not be a person traveling as a companion on the UM modal
And I complete the UM Guardian information for the round-trip itinerary
Then I check New Credit Card still selected