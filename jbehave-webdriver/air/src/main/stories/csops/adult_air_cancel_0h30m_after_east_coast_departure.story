Meta:
@flow air
@process cancel
@user anonymous
@traveler adult
@dyna_stubs
@not_live

Narrative:
In order to see oops message for trying to cancel my flight 30 min. after the departure time
As an anonymous user
I want to cancel my flight

Given I have booked a flight from PVD to GEG departing at -0h30m
When I attempt to cancel the flight
Then I see an Oops messages from cancel air page indicating that The confirmation number entered is for a travel date in the past and cannot be retrieved online
