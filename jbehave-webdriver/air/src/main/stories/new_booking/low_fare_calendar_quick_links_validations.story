Shortcut Enhancement - Several Quick Hits - Search Flight Page

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@not_passing broken

Narrative:
In order to validate several quick links enhancement in the low fare calendar page
as a user
I want to search for the lowest fares by month

Given I am traveling as a:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And The following routes are available:
    |Field|Value|
    |originStation|DAL|
    |destinationStation|HOU|
    |returnStation|DAL|
    |carrierDates|WN:T:[today+0,today+115]|

And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DAL|
    |arrivalStation|HOU|
    |departingFlight_carrierCode|WN|
    |arrivingFlight_carrierCode|WN|
    |departureDate|+30|
    |returnDate|+60|

When I search for my flight from the low fare calendar page
Then I should see the Shortcut Select Flight page
