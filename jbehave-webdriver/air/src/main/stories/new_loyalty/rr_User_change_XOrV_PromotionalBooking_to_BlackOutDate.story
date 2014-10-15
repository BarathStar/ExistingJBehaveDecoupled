Verify that Search Results are grayed out for changing a promo cert booking to a blackout date return

Meta:
@flow air
@process change
@user rr_member
@traveler adult
@project JL_CR20_X_V_PROMOCERT
@not_passing
@project_in_dev
@storyid ZR-862

Narrative:
As a Rapid Rewards Customer
I want to be able to verify that I am unable to change an existing reservation that used a Promo Cert for X and/or V fare class codes created on or after 10/1/2012

Scenario:  Show selected new promo cert for changed trip on reconciliation page
Given I have the following itinerary data:
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
And I am a Rapid Rewards Member with two active X and V promotional certificates with unavailable date on itinerary departure date plus two
And I have booked this flight using an X and V promotional certificate that have a blackout date on itinerary departure date plus two
When I retrieve my reservation for change
And I select to change my entire itinerary and continue to the Select New Flight page
Then I should see flights unAvailable in the select flight page in promotional certificate column And the results grayed out
