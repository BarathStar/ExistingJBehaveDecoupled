Retrieves an Air product without being shown any error message even though the Trip Management Database is down

Meta:
@flow air
@process view
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@project tmAir

Narrative:
In order to not see an error message when I retrieve my trip
As an Anonymous User
I want to retrieve the Air product even though the Trip Management Database is down

Scenario: Anonymous User retrieves an Air product without being shown any error message even though the Trip Management Database is down
Given I have the Trip Management Database down
And I am:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have selected the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|HOU|
    |departureDate|+1|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|

And I have included an Air in my shopping cart
And I have finished my purchase adding my product to a new trip named My Trip
When I retrieve my Air itinerary
Then I see my air product on the Retrieve Itinerary page
And I see on the View Reservation page that the Air product has the default trip name