Check the default trip name and all its Add-ons, which will NOT display a Hotel Dynamic Cross-Sell

Meta:
@flow air
@process view
@user anonymous
@traveler adult
@dyna_stubs
@not_live


Narrative:
In order to view a reservation for an Air product which does not belong to a trip
As an Anonymous User
I want to retrieve the Air reservation by Credit Card and see all its details

Scenario: Anonymous User retrieves an Air Reservation (which does not belong to a trip) by Credit Card and checks its Add-ons. View Itinerary page which will NOT display a Hotel Dynamic Cross-Sell.
Given I am traveling as a:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|MDW|
    |departureDate|+2|
    |arrivalStation|LGA|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|Nonstop|

And I have searched and booked a flight without adding this product to a trip
When I retrieve my air itinerary by Credit Card
Then I should not see the Air Disambiguation Page
And I see on the View Reservation page that the Air product has the default trip name
And I see the Air product details
And I should not see items associated to the trip
And I should not see the Cross Sell Sections
And I should not be able to add the Early Bird Check-in to my Air product
And I see an icon which informs that the air product has been purchased on Business Select on the View Reservation Page
And I should not see the Hotel Dynamic Cross-Sell