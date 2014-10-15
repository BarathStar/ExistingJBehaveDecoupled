Search for a flight from the promotion certificates list

Meta:
@flow air
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs
@not_live

Narrative:
As a RapidRewards Member
I want to see multiple fare classes in select flight page after login into my account and try to select flights using my promotional certificates

Scenario: RR Member views a single promotion
Given I am a Rapid Rewards Member with two active promotional certificates in my account
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
    |departingFlight_fareClass|Anytime|
    |departureDate|+1|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |returnDate|+2|
    |adultPassengerCount|1|
When I search flights using a promotional certificate
Then I should see multiple fare class columns from select flight page
