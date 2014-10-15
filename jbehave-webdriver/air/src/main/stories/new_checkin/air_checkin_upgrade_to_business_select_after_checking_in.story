Check-in round-trip anytime air ticket for an adult after upgrading to Business Select

Meta:
@suite regression
@flow air
@process booking
@traveler adult
@dyna_stubs
@not_live

Narrative:
In order to fly a business select
As an adult
I want to upgrade to business select once i had already checked-in for a flight

Scenario: Check in for a WN-WN Itinerary with an attempt to upgrade to Business Selection
Given I am traveling as a:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

When I search and book a flight eligible for checkin from search flights page
And I click on the Check in button on the Confirmation Page
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view my boarding pass
When I return to the checkin page
And I choose to upgrade to business select
Then I should see the itinerary change confirmation page
