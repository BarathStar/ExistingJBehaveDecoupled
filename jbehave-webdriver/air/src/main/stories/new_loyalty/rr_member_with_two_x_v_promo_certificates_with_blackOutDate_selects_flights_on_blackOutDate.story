Verify flights are unavailable during the blackout period when user booking using promotional certificates with blackout/unavailable dates

Meta:
@flow air
@process booking
@user rr_member
@traveler adult
@project JL_CR20_X_V_PROMOCERT
@dyna_stubs
@not_live
@project_in_dev
@storyid ZR-839

Narrative:
As a RapidRewards Member
I should see flights unAvailable in select flight page on a blackout date for the promotional product after login into my account and try to select flights using my x and v promotional certificates

Scenario: RR Member will see unAvailable flights during blackout period for the promotional product
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
And I am a Rapid Rewards Member with two active X and V promotional certificates with unavailable date on itinerary departure date
When I log in from the account sidebar at the Search Flight page
And I expand My Rapid Rewards section
Then I see my promotion with its expiration date
When I request to see my promotions from the account bar
Then I view my active promotions in my account
When I search flights using a promotional certificate
Then I should see flights unAvailable in the select flight page in promotional certificate column And the results grayed out
