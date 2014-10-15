Verify Passenger Information Is Preloaded On The Purchase Page

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
I want to see the passenger information preloaded on the Purchase page
and it has to match with the Rapid Rewards account information

Scenario: Verify passenger information is preloaded on the Purchase page
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
Then I see the passenger information correctly filled in on the Purchase page