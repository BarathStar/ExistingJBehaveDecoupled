A-List Member views a flight with EB check-in, which will NOT display a Hotel Dynamic Cross-Sell

Meta:
@flow air
@process view
@user rr_member
@traveler adult
@dyna_stubs
@not_live


Narrative:
In order to check an Open Jaw EB eligible reservation which is not part of a trip
As an A-List Member
I want to retrieve the reservation, see all its details and not be able to name the trip

Scenario: A-List Member views an Open Jaw Air reservation with Early Bird check-in for two passengers. View Itinerary page which will NOT display a Hotel Dynamic Cross-Sell.
Given I am logged-in as an A-List Member on the Search Flights page
And I am:
    |Field|Value|
    |adultPassengerCount|2|
    |seniorPassengerCount|0|

And I have selected the following itinerary data:
    |Field|Value|
    |itineraryType|Open Jaw|
    |departureStation|DAL|
    |departureDate|+2|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivalStation|SAT|
    |returnStation|HOU|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

And I have included an Air in my shopping cart
And I have finished an Early Bird purchase without adding the Air product to a trip
When I retrieve my itinerary
Then I see on the View Reservation page that the Air product has the default trip name
And I am not able to change the trip name
And I see the itinerary dates formatted like mm/dd/yyyy - mm/dd/yyyy
And I see the cities of my open jaw flight
And I see an icon which informs that the EB Checkin has been added to the flight on the View Reservation Page
And I see an icon which informs that the passenger is an A-List Member
And I should not see the Hotel Dynamic Cross-Sell