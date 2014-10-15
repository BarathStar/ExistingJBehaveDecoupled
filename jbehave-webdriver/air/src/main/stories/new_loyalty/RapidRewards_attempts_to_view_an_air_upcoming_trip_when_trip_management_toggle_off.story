View upcoming trip details when trip management toggle is off.

Meta:
@flow rr
@process loyalty
@user rr_member
@not_live
@dyna_stubs
@trip_management_off

Narrative:
As a Rapid Rewards Member (with Upcoming Trips)
I want to see my upcoming trip details on "My Account Snapshot" page and view details in the "Upcoming Trips" section when trip management toggle is off

Scenario: Rapid Rewards Member attempts to view upcoming trip details page under "Upcoming Trips" section on "My Account Snapshot" page
Given I am a Rapid Rewards Member passenger
And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |departureDate|+1|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

And I have included an Air in my shopping cart
And I have finished my purchase adding my products to a new trip named My Trip
And The Trip Management toggle is Off
When I log in from the account sidebar at the Search Flights page
And I click my account link
And I click to see details of my upcoming trip
Then I see the details of the Air reservation on the Trip Details page when trip management toggled off
