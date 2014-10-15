Verify coming soon oops message when user books an Outbound before FL[I] ends and WN[I] begins and IB after WN[I] begins and FL[I] ends when FL[I] service ends after WN[I] service begins

Meta:

@project coda
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@project_in_dev
@storyid DCAIR-8083

Narrative:

As a Customer
I want to search for a WN/FL shared international city pair that Airtran is serving and Southwest is not serving for the requested outbound date
and Airtran has stopped serving and Southwest is serving for the requested inbound date
when Airtran service ends after Southwest service begins
I will see an oops message that service is yet to begin


Scenario: An adult searches for a Southwest International flight

Given I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DAL|
    |arrivalStation|MEX|
    |departureDate|+20|
    |returnDate|+70|
And The following routes are available:
    |Field|Value|
    |originStation|DAL|
    |destinationStation|MEX|
    |carrierDates|FL:F:[today,today+30]|
And The following routes are available:
    |Field|Value|
    |originStation|MEX|
    |destinationStation|DAL|
    |carrierDates|FL:F:[today,today+30]|
And The following routes are available:
    |Field|Value|
    |originStation|DAL|
    |destinationStation|MEX|
    |carrierDates|WN:F:[today+25,today+90]|
And The following routes are available:
    |Field|Value|
    |originStation|MEX|
    |destinationStation|DAL|
    |carrierDates|WN:F:[today+25,today+90]|

When I am searching flights from home page
Then I see an Oops messages from search flight page indicating that Published scheduled service between (Dallas - DAL) and (Mexico City - MEX) does not begin until
