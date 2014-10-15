Verify coming soon oops message when user books an Outbound before FL[I] ends and Inbound after WN[I] begins

Meta:

@project coda
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@project_in_dev
@storyid DCAIR-8057

Narrative:

As a Customer
I want to search for a WN/FL shared international city pair that Airtran is serving and Southwest yet to begin for the requested outbound date
and Airtran has stopped serving and Southwest is serving for the requested inbound date
I will see an oops message that service is yet to begin

Scenario: An adult searches for a Southwest International flight

Given I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DAL|
    |arrivalStation|CUN|
    |departureDate|+20|
    |returnDate|+70|
And The following routes are available:
    |Field|Value|
    |originStation|DAL|
    |destinationStation|CUN|
    |carrierDates|FL:F:[today,today+30]|
And The following routes are available:
    |Field|Value|
    |originStation|CUN|
    |destinationStation|DAL|
    |carrierDates|FL:F:[today,today+30]|
And The following routes are available:
    |Field|Value|
    |originStation|DAL|
    |destinationStation|CUN|
    |carrierDates|WN:F:[today+60,today+90]|
And The following routes are available:
    |Field|Value|
    |originStation|CUN|
    |destinationStation|DAL|
    |carrierDates|WN:F:[today+60,today+90]|

When I am searching flights from home page
Then I see an Oops messages from search flight page indicating that Published scheduled service between (Dallas - DAL) and (Cancun - CUN) does not begin until
