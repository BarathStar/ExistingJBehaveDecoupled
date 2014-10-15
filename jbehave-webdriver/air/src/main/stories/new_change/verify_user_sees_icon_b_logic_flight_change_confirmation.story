Verify that Southwest.com user sees added the B Icon Logic into Change Flight Confirmation page.

Meta:
@flow air
@process change
@user rr_alist
@passenger adult
@dyna_stubs
@not_live

Narrative:
As a southwest.com user changing my flight
I want to see added the B Icon Logic on Change Flight Confirmation page
So that I can see the same icons next to the passenger name(s) as are currently displayed on the "Select Flights to Change" page.

Scenario:
Validate that Southwest.com user sees the same B Icon Logic on Change Flight Confirmation page next to the passenger name(s) as are currently displayed on the "Select Flights to Change" page.
Given I login as an Alist Preferred Rapid Rewards member on the Rapid Rewards Account page
And air itinerary data as follows:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|
    |itineraryType|One Way|
    |departureStation|LAS|
    |arrivalStation|SMF|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|Nonstop|
    |departureDate|+14|

And I have a flight booked
And I am changing the following itinerary data to:
    |Field|Value|
    |departingFlight_fareClass|BusinessSelect|
And I retrieve my reservation for change

When I change the flight
Then I see B icon displayed next to the passenger name on Change Flight Confirmation page