Change an itinerary originally booked with a freedom award.

Meta:
@flow air
@process change
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
In order to change a reservation originally booked with a freedom award
As a Rapid Rewards Member who has only Standard Awards on his account
I want to start the change process and be shown all the information related to the freedom award used in the booked flight when I am asked to select a new flight

Scenario: Rapid Rewards Member (with only standard awards) attempts to change an itinerary originally booked with a freedom award
Given I am a Rapid Rewards Member passenger with freedom awards
And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |departureDate|+4|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|FreedomAward|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|FreedomAward|
    |inboundRouting|Nonstop|
And I have booked this flight
And I change to a new Rapid Rewards User with Standard Awards
When I login from Login page
And I want to change my itinerary
And I select to change my entire itinerary and continue to the Select New Flight page
Then I see a message which informs that Freedom Awards cannot be combined with tickets purchased with dollars
And I see the expiration date of the freedom award used when the flight was booked
And I should not see the dollar-points toggle
And I see Freedom Award as the only fare available for both outbound and inbound flight