Initial Booking Fare breakdown validations on Price and Confirmation page

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to validate all the available options for an fare breakdown table on Price page and Confirmation Page
As a User
I want to be shown the fare breakdown table on Price page and Confirmation Page

Scenario: Fare breakdown validations on Price and Confirmation page
Given I am flying a round-trip SouthwestNS flight with Nonstop segments
When I get to the Price page
Then I see the Fare Breakdown flyOut
When I continue to the purchase page and complete the booking
When I click on the Fare Breakdown link on the Confirmation page
Then I see that the Base Fare, Taxes and Total are the same as the ones displayed on the Price Page