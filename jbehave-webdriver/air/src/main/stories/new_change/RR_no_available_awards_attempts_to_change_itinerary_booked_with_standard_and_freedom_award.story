Change a reservation originally booked with a standard and freedom award so that I can see the information of the awards using in the flight booked

Meta:
@flow air
@process change
@user rr_member
@traveler adult
@dyna_stubs
@not_live

Narrative:
In order to change a reservation originally booked with freedom and standard awards
As a Rapid Rewards Member with no available awards
I want to start the change process and be shown all the information related to the freedom award used in the booked flight when I am asked to select a new inbound flight

Scenario: Rapid Rewards Member (with no available awards) attempts to change the inbound flight of an itinerary originally booked with freedom and standard awards
Given I am a Rapid Rewards Member with both standard and freedom awards
And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |departureDate|+4|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|StandardAward|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|FreedomAward|
    |inboundRouting|Nonstop|

And I have booked this flight
And I change to a new Rapid Rewards User with No awards
When I login from Login page
And I want to change my itinerary
And I select to change the inbound flight and continue to the Select New Flight page
Then I see a message which informs that Standard and Freedom Awards cannot be combined with tickets purchased with dollars
And I see the expiration date of the freedom award used when the flight was booked
And I should not see the dollar-points toggle
And I see Freedom Award as the only fare available for the inbound flight