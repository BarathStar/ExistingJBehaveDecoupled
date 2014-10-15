View Southwest Fly out on Select Flight Page

Meta:
@project cr1
@flow air
@process view
@user anonymous
@traveler adult
@storyId DCAIR-4792, ZR-899
@dyna_stubs
@project_in_dev

Narrative:
As a customer
I want to search for southwestconnect flight with 1 stop segments
So that I view the airline logo when I select routing on select flights page.


Scenario: Flyout Check SouthWest Logo

Given I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|PDX|
    |arrivalStation|LAX|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

When I only search a flight
When I click the Nonstop link for WN on the select flight page
Then I view the airline logo
