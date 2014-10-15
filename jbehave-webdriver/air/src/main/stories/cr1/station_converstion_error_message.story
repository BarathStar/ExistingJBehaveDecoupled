Error message displayed when AirTran station discontinued

Meta:
@suite regression
@flow air
@traveler adult
@project webreferral
@storyId DCAIR6975 ,6986
@message special case can not be dyna stub
@project webreferral
@airTranOnly

Narrative:
In order to view error message when AirTran station has been discontinued
As a Customer
I want to see an error message indicating AirTran route currently unavailable

Scenario: Display error message indicating AirTran route currently unavailabe

Given I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|

And air itinerary data as follows:

   |Field|Value|
   |itineraryType|Round Trip|
   |departureStation|SEA|
   |arrivalStation|CAK|
   |departingFlight_carrierCode|FL|
   |departingFlight_fareClass|AnyTime|
   |arrivingFlight_carrierCode|FL|
   |departureDate|+90|

When I am searching flights from plan trip page
Then I see an Oops messages from plan trip page indicating that Published scheduled service between (Seattle - SEA) and (Akron - CAK) does not begin until 08/19/2012
