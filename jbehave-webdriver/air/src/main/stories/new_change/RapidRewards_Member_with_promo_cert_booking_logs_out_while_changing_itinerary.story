Log out while changing an itinerary originally booked with a freedom award.

Meta:
@flow air
@process change
@user rr_member
@traveler adult
@dyna_stubs
@not_live
@project_in_dev
@storyid DCAIR-8175

Narrative:
I am logged in as a member with a promotional certificate pnr and I try to change this pnr.
When I proceed to select new flights page and I log out, I should be redirected to the login page

Scenario: Rapid Rewards Member logs out from select new flight page in the change flow while attempting to change an itinerary originally booked with a promotional certificate

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
And I log out from my Rapid Reward Account
Then I see the My Account Login page
