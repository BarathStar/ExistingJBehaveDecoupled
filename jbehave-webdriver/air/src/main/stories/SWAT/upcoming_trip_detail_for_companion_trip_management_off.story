See upcoming trip details having a companion booking in my upcoming trips with trip management off as an user

Meta:
@flow rr
@process view
@user rr
@traveler adult
@dyna_stubs
@not_live
@project_in_dev
@project SWAT
@trip_management_off

Narrative:
As an user
I want to see my trip details without companion passenger even if trip management is Off
So that I can see details page for my second flight

Scenario: User see details upcoming trip for a flight without companion with trip management Off

Given I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DCA|
    |arrivalStation|AUS|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

And I have an air reservation for a member with companion pass achieved and boarding pass issued
And I have an air reservation for a companion passenger with boarding pass issued
And I have booked a round-trip Southwest Southwest flight for an adult
And The Trip Management toggle is Off
And I have logged in and I am in view all my upcoming trips
When I click on my last reservation link to see the flight details
Then I see the Upcoming Trip Details Page