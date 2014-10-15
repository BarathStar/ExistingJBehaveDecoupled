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
    |departureDate|+1|
    |returnDate|+90|

When I search for my flight from the low fare calendar page
And I select the previous month for my returning flight
Then I verify that departure month has not changed
When I select the following month for my departing flight
Then I verify that return month has not changed