UM validations on Confirmation page

Meta:
@flow air
@process booking
@traveler um
@user anonymous
@project UM/YT
@dyna_stubs
@not_passing takestoolongtorun

Narrative:
In order to validate all the available options for an UM (pax age: 5-11) Passenger on Confirmation Page
As an anonymous user
I want to be shown the UM information on Confirmation Page and not be able to add Early Bird

Scenario: UM validations on Confirmation page
Given I am flying a round-trip SouthwestNS flight with Nonstop segments departing tomorrow and returning day-after
And I am a minor traveling alone
When I get to the Price page
Then I see the Fare Breakdown flyOut
When I continue to the purchase page and complete the booking for an Unaccompanied Minor
Then I should not be able to add Early Bird Check-In to my flight
Then I see information about the UM and the applied charges for a RT with 1 minor on the Confirmation page
When I click on the Fare Breakdown link on the Confirmation page
Then I see that the Base Fare, Taxes and Total are the same as the ones displayed on the Price Page
