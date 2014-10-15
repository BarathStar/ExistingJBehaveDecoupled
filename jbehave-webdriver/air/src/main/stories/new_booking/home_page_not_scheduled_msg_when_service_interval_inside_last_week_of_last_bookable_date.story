Verify not scheduled exception instead of discontinued for a WN international route when searching for a round-trip with departure after service end and inside last week of last bookable date

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@storyId DCAIR-8191

Narrative:
As a Customer
When I search for an international city pair with departure date falling inside of last week of last bookable date
Then I see the not scheduled exception instead of discontinued exception on home page

Scenario: An adult searches for an Southwest international flight on home page with departure date falling inside last week of last bookable date

Given I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|ATL|
    |arrivalStation|NAS|
    |departureDate|+35|
    |returnDate|+118|
And The following routes are available:
    |Field|Value|
    |originStation|ATL|
    |destinationStation|NAS|
    |carrierDates|FL:F:[today,today+7]|
    |carrierDates|WN:F:[today,today+45]|
And The following routes are available:
    |Field|Value|
    |originStation|NAS|
    |destinationStation|ATL|
    |carrierDates|FL:F:[today,today+7]|
    |carrierDates|WN:F:[today,today+115]|
And I am on the Home page, fill in itinerary data and search for flights
Then I see an Oops messages from home page indicating that Flights from Nassau - NAS to Atlanta - ATL are not scheduled to operate
