Verify Itinerary is available for booking on airtran.com

Meta:
@suite regression
@flow air
@process booking
@traveler adult
@dyna_stubs
@storyId DCAIR6962
@project webreferral
@not_passing https://saucelabs.com/jobs/1607fab3182c440298f5704865bc9de7#  [see video : station CAK not found]
@airTranOnly

Narrative:
In order to show the booking is available on airtran.com
As a Customer
I want to select an AirTran pair that are not serviced by southwest.com

Scenario: Show Redirect Model on Marketing Page if the route is included in the AirTran redirect routes and if Southwest has schedules for this route

Given I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|

And air itinerary data as follows:

   |Field|Value|
   |itineraryType|Round Trip|
   |departureStation|CAK|
   |arrivalStation|DEN|
   |departingFlight_carrierCode|FL|
   |departingFlight_fareClass|AnyTime|
   |arrivingFlight_carrierCode|FL|
   |departureDate|+30|
   |returnDate|+90|

When I am searching flights from marketing page
Then view the AirTran Redirect Modal with WCM Content
