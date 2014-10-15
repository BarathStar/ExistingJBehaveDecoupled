Verify no error message is displayed even though the Trip Management Database is down.

Meta:
@flow air
@process cancel
@user anonymous
@traveler adult
@dyna_stubs
@not_live

Narrative:
In order to not see an error message when I reach Cancel Air Reservation page
As an Anonymous User
I want to retrieve the Air product and reach Cancel Air Reservation page even though the Trip Management Database is down

Scenario: Anonymous User accesses Cancel Air Reservation page without being shown any error message even though the Trip Management Database is down
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
And I have finished my purchase adding my products to a new trip named My Trip
When I access the cancel air reservation option
Then I am redirected to the Cancel Air Reservation page
When I retrieve the Air reservation to cancel it
Then I see my air product on the Air Cancellation page