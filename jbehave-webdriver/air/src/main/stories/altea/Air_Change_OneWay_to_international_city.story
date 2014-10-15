Air Change Single Anonymous Adult changes international flight for an adult

Meta:
@project coda
@flow air
@process change
@user anonymous
@traveler adult
@not_live
@project_in_dev
@dyna_stubs
@note we are using ABQ for temporary international route because trunk is not yet CODA compliance

Narrative:
In order to verify as an anonymous Single Adult user on the Book Travel section of southwest.com
As a user
I want to change the Nonstop domestic flights international flight so that I can successfully make a change to booked itinerary

Scenario: Change Itinerary - Changing the arrival city in WN OneWay flight itinerary
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|DAL|
    |arrivalStation|SAT|
    |departingFlight_carrierCode|WN|
    |arrivingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |adultPassengerCount|1|
    |departureDate|+7|

And I have a flight booked
And The following routes are available:
    |Field|Value|
    |originStation|HOU|
    |destinationStation|ABQ|
    |carrierDates|WN:F:[today,today+30]
And I am changing the following itinerary data to:
    |Field|Value|
    |departureStation|HOU|
    |arrivalStation|ABQ|

When I retrieve my reservation for change
And I select and enter departure/arrival station on change itinerary page
And I verify changeTripPage oops Looks like you are attempting to change your domestic U.S. itinerary to an international itinerary.
And I click cancel on oops message cancel link for international route
Then I should see confirmation number on Cancel Air Reservation page

