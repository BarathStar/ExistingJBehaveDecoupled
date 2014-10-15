Change an itinerary originally booked with a freedom award.

Meta:
@flow air
@process change
@user rr_member
@traveler adult
@project JL_CR20_X_V_PROMOCERT
@dyna_stubs
@not_live
@project_in_dev
@storyid ZR-758

Narrative:
In order to change a reservation originally booked with an x or v promotional certificate
I want to start the change process and be shown all the information related to the promotional certificate used in the booked flight when I am asked to select a new flight

Scenario: Rapid Rewards Member (with promotional certificates) attempts to change an itinerary originally booked with a promotional certificate
Given I am a Rapid Rewards Member with two active X and V promotional certificates in my account
And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |departureDate|+5|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|FreedomAward|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|FreedomAward|
    |inboundRouting|Nonstop|
    |returnDate|+6|
    |adultPassengerCount|1|
And I have booked this flight using an X and V promotional certificate
When I login from Login page
And I want to change my itinerary
And I select to change my entire itinerary and continue to the Select New Flight page
Then I should see single promo certificate fare class column from select flight page