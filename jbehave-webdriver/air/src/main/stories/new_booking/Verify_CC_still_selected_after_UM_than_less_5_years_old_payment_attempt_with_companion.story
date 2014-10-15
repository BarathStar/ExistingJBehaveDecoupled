Verify Credit Card still selected after making a purchase for child under 5 years old at payment attempt

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
As an infant
I try book a flight for a child under 5 years old and I want to see preview Credit Card selected
So that

Scenario: Verify Credit Card still selected after making a purchase for child under 5 years

Given I am a Rapid Rewards Member
And I login from Login page
And I have stored 1 credit cards
And I have booked a round-trip SouthwestUMRoute SouthwestUMRoute flight for an adult
And I am flying a round-trip SouthwestUMRoute SouthwestUMRoute flight
And I am an Infant traveling with a companion
When I attempt to book a flight with an itinerary which matches the itinerary booked before for the adult
And I confirm there will be a person traveling as a companion on the UM modal
And I retrieve my booked flight on the Accompanying Traveler Form
And I click on the continue button on the accompanying adult form
Then I check New Credit Card still selected