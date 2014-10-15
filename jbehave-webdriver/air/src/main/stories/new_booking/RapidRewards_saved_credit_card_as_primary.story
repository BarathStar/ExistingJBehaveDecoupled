Stored Credit Card On The Purchase Page Should Be Mark As Primary

Meta:
@flow air
@process booking
@user rr_member
@traveler adult
@dyna_stubs
@not_live

Narrative:
In order to do a flight booking
As a Rapid Rewards Member
I want to verify that the stored credit card on the Purchase page is mark as primary on the Snapshot page

Scenario: verify if stored credit card on the Purchase page is set as primary
Given I have the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|DAL|
    |arrivalStation|HOU|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|

And I am a Rapid Rewards Member passenger
When I log in from the account sidebar at the Search Flights page
And I am on the purchase page
And I complete the booking process adding the credit card to the account
And I click my account link
And I select the preference page
And I select the Payment Information link
Then I see that the credit card was set as primary