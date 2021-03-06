Verify the rapid rewards summary page shows up with the right promotional certificates.

Meta:
@flow air
@process booking
@user rr_member
@traveler adult
@project JL_CR20_X_V_PROMOCERT
@dyna_stubs
@not_live
@project_in_dev
@storyid ZR-838

Narrative:
As a RapidRewards Member
I want to see rapid reward awards summary on the purchase page after login into my account and try to select flights using my x and v promotional certificates

Scenario: RR Member views the rapid rewards summary on the purchase page
Given I am a Rapid Rewards Member with two active X and V promotional certificates in my account
When I log in from the account sidebar at the Search Flight page
And I expand My Rapid Rewards section
Then I see my promotion with its expiration date
When I request to see my promotions from the account bar
Then I view my active promotions in my account
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DAL|
    |arrivalStation|HOU|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|StandardAward|
    |departureDate|+1|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|StandardAward|
    |returnDate|+2|
    |adultPassengerCount|1|
When I search flights using a promotional certificate
Then I should see flights available in the select flight page in promotional certificate column
When I select, price and view the Purchase page for a flight
Then I should see the rapid rewards awards displayed on the purchase page