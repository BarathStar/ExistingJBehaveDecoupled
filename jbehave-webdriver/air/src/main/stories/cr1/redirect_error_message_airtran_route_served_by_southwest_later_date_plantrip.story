Verify error message on flight search page

Meta:
@suite regression
@flow air
@traveler adult
@project webreferral
@storyId DCAIR6986
@message special case can not be dyna stub
@airTranOnly

Narrative:
In order to view error message when booking a route shared in the future by Southwest and AirTran
As a Customer
I want to see an error message redirecting AirTran routes being served by Southwest on a future date

Scenario: Display Error Message which redirects to AirTran for routes being served by Southwest on a future date from Plan Trip Page

Given I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|

And air itinerary data as follows:

   |Field|Value|
   |itineraryType|One Way|
   |departureStation|CAK|
   |arrivalStation|DEN|
   |departingFlight_carrierCode|FL|
   |departingFlight_fareClass|AnyTime|
   |arrivingFlight_carrierCode|FL|
   |departureDate|+90|

When I am searching flights from plan trip page
Then I see an Oops messages from plan trip page indicating that please visit airtran.com to make this booking
