Shortcut Enhancement - Several Quick Hits - Select Flight Page

Meta:
@suite regression
@flow air
@process select
@traveler adult
@storyId GLO-2089
@not_passing draft

Narrative:
In order to validate depart/return month/year in the shortcut select flights page
as a user
I want to change them and verify dates consistency

Given I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DAL|
    |arrivalStation|HOU|
    |departingFlight_carrierCode|WN|
    |arrivingFlight_carrierCode|WN|
    |departureDate|+30|
    |returnDate|+90|

When I search for my flight from the low fare calendar page
When I select a departure month greater than the returning month
Then I verify that return month is the same as my departure month
When I select a return month less than the departure month
Then I verify that return month is the same as my departure month
