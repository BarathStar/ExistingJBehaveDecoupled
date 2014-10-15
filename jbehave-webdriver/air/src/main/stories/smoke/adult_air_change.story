Change existing adult flight itinerary

Meta:
@suite smoke
@flow air
@process change
@user anonymous
@traveler adult
@storyId DCQA216
@dyna_stubs
@project_AccordionPage
@homepage2

Narrative:
In order to fly on a date that I can
As an adult
I want to travel two days later than my original date and receive a updated itinerary

Scenario: adult air change

Given I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |arrivalStation|DAL|
    |departureDate|+2|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|
    |returnDate|+5|

When I have a flight booked
And I change the flight to a later date
Then I should see the itinerary change confirmation page
