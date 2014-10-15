Change and be informed that the reservation to change was booked with this kind of award

Meta:
@flow air
@process change
@user rr_member
@traveler adult
@dyna_stubs
@not_live

Narrative:
As a Rapid Rewards Member with no available awards in his account
I want to start the change process and be shown the information related to the standard award used in the booked flight when I am asked to select a new flight
In order to change a reservation originally booked with a standard award

Scenario: Rapid Rewards Member (with no available awards) attempts to change an itinerary originally booked with a standard award
Given I am a Rapid Rewards Member passenger with reissuable Standard Awards in my account
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
    |arrivingFlight_fareClass|StandardAward|
    |inboundRouting|Nonstop|

And I have booked this flight using all the available standard awards in my account
And I change to a new Rapid Rewards User with No awards
When I login from Login page
And I retrieve the Air reservation to attempt to change it
Then I am informed that my round trip flight was booked by using standard awards
