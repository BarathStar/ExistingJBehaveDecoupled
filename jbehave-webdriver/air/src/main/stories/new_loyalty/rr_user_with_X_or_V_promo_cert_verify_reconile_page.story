Verify that I was able to change an existing reservation that used a Promo Cert for X and/or V fare class codes created on or after 10/1/2012

Meta:
@flow air
@process change
@user rr_member
@traveler adult
@project JL_Coda_CR20
@not_passing
@project_in_dev
@storyid ZR-836

Narrative:
As a Rapid Rewards Customer
I want to be able to verify that I was able to change an existing reservation that used a Promo Cert for X and/or V fare class codes created on or after 10/1/2012

Scenario:  Show selected new promo cert for changed trip on reconciliation page
Given I am a Rapid Rewards Member with two active X or V promotional certificates in my account
And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |departureDate|+5|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|StandardAward|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|StandardAward|
    |inboundRouting|Nonstop|
    |returnDate|+6|
    |adultPassengerCount|1|
And I have booked this flight using an X and V promotional certificate
When I login from Login page
And I select to change my entire itinerary and continue to Reconcile page
Then I should see the rapid rewards awards displayed on the purchase page
