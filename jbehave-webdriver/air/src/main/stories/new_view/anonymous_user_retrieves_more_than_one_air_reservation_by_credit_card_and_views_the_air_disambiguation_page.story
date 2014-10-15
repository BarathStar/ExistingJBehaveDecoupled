Retrieves more than one Air reservation by Credit Card

Meta:
@flow air
@process view
@user anonymous
@traveler adult
@dyna_stubs
@project tmAir

Narrative:
In order to see the Air Disambiguation page
As an Anonymous User
I want to retrieve all the Air reservations purchased with the same Credit Card number, passenger first and last name, departure city and date

Scenario: Anonymous User retrieves Air Reservations by Credit Card and views the Air Disambiguation page
Given I am traveling as a:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|SAT|
    |departureDate|+1|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|

And I have purchased two separate Air products without adding them to trips and with the same information
When I retrieve my Air itinerary by Credit Card
Then I see the Disambiguation Page
And I see the Air products' Confirmation Numbers
When I check the details of the first Air reservation listed
Then I see on the View Reservation page that the Air product has the default trip name
And I see the Air product details
And I should not see items associated to the trip
And I should not see the Cross Sell Sections