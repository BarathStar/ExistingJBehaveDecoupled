Verify discontinued for a WN international route when service interval is outside last week of last bookable date and travel date is after service end

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@storyId DCAIR-8191

Narrative:
As a Customer
When I search for an international city pair with departure date after schedule end date and service end outside last week of last bookable date
Then I see the discontinued exception on home page

Scenario: An adult searches for an Southwest international flight on home page with departure date after schedule end date and service end outside last week of last bookable date

Given I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DEN|
    |arrivalStation|NAS|
    |departureDate|+35|
    |returnDate|+40|
And The following routes are available:
    |Field|Value|
    |originStation|DEN|
    |destinationStation|NAS|
    |carrierDates|FL:F:[today,today+7]|
    |carrierDates|WN:F:[today,today+30]|
And The following routes are available:
    |Field|Value|
    |originStation|NAS|
    |destinationStation|DEN|
    |carrierDates|FL:F:[today,today+7]|
    |carrierDates|WN:F:[today,today+45]|
And I am on the Home page, fill in itinerary data and search for flights
Then I see an Oops messages from home page indicating that Published scheduled service between (Denver - DEN) and (Nassau - NAS) will be discontinued
