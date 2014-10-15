Verify no error message is displayed even though the Trip Management Database is down

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@suite regression
@dyna_stubs
@not_live
@project tmAir

Narrative:
In order to not see an error message when I reach Change Itinerary page
As an Anonymous User
I want to retrieve the Air product and reach Change Itinerary page even though the Trip Management Database is down

Scenario: Anonymous user accesses Change Itinerary page without being shown any error message even though the Trip Management Database is down
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
When I want to change my itinerary
Then I see Southwest Airlines - Change Itinerary page