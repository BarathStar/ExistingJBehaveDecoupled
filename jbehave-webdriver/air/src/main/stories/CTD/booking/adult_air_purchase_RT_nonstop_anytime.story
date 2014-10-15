Initial Purchase - Round Trip Nonstop Anytime

Meta:
@dyna_stubs
@suite regression
@project ctd
@flow air
@process booking
@traveler adult
@storyId SWACOMTT976
@project_AccordionPage
@not_passing  .StaleElementReferenceException: Element not found in the cache   : job/Trunk_Test_JBehave_CTD_DynaStubs_Booking/1683

Narrative: In order to verify user can book an itinerary
As a user
I want to book an itinerary for Adult Round Trip Nonstop Anytime

Scenario: Book an itinerary for Adult Round Trip Nonstop Anytime

Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DAL|
    |arrivalStation|STL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|
    |departureDate|+9|
    |returnDate|+10|

When I book a flight
Then I receive an air confirmation number
