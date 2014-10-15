Meta:
@flow air
@process checkin
@user anonymous
@traveler adult
@dyna_stubs
@not_live

Narrative:
As an anonymous user
I want to check-in 25 hours prior
So that I can receive an Oops message letting me know that I am not eligible

Scenario: Online Adult Checkin more than 24h before flight is not allowed

Given I have booked a flight from PVD to GEG departing at +24h30m
When I attempt to view the checkin details for my flight
Then I see an Oops messages from check in page indicating that The request to check in and print your Boarding Pass is more than 24 hours prior to your scheduled departure
