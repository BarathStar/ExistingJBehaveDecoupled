Verify Correct Oops message when IET Hub outage during cancel flow

Meta:
@project cr1
@flow air
@process cancel
@traveler adult
@dyna_stubs
@not_live

Narrative:
When I try to cancel an existing codeshare flight
And the IET hub is not available
I should see the appropriate Oops message

Scenario: Cancelling Codeshare Flight during IET hub outage

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|FLL|
    |arrivalStation|BWI|
    |departingFlight_carrierCode|FL|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

And I have an air reservation but IET hub is down
When I retrieve the Air reservation to cancel it
And I choose to return funds and continue
Then I see an Oops messages from cancel air page indicating that Cancel Reservation is currently unavailable