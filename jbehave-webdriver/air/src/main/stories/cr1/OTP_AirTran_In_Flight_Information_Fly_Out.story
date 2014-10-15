On Time Performance in Flight information fly-out for AirTran

Meta:
@project cr1
@flow air
@process view
@airTranOnly
@user anonymous
@traveler adult
@dyna_stubs
@storyId DCAIR-7269, ZR-892
@not_passing draft due to Airtran verbiage not found in flyout. Possible bug when OTP data not available for flight


Narrative:
As a customer
I want to search for Nonstop Airtran flight segment from Search Flights
So that I can view Ontime Performance data when I click on the first nonstop flight number link on the Select Flight page


Scenario: User sees the flight on time performance information for a nonStop AirTran flight

Given I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|FLL|
    |arrivalStation|ATL|
    |departingFlight_carrierCode|FL|
    |departingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

When I only search a flight
And I click on the first NonStop flight number link on the select flight page
Then I see the AirTran flight information in the single fly-out
