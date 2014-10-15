Handle missing search criteria due to invalid session

Meta:
@suite regression
@flow air
@not_live
@process select
@user anonymous
@story_id BUG-2572

Narrative:
When I am not not in and go to the shortcut select page calendar and then bring up the trip as dream trip. I should see invalid
session state page

Scenario: Shortcut calendar select flight

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
And I get the select flights page from dream trips
Then I see an invalid session message