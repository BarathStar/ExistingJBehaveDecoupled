Verify Itinerary is available for booking on airtran.com

Meta:
@suite regression
@flow air
@process booking
@traveler adult
@storyId DCAIR6962
@project webreferral
@airTranOnly

Narrative:
In order to show the booking is available on airtran.com
As a Customer
I want to select an AirTran pair that are not serviced by southwest.com

Scenario: Oops Message on Search Page with airtan.com link if the route is AirTran redirect routes and if Southwest has schedules for this route

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
When I am searching flights from flight search page
Then I see an Oops messages from flight search page indicating that please visit airtran.com to make this booking