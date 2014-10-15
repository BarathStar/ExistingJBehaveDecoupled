Verifies Update to Business Select messages and actions when a User is checking in and Upgrade is available.

Meta:
@flow air
@process checkin
@user adult
@traveler anonymous
@dyna_stubs

Narrative:
In order to validate that Upgrade to Business Select Fare option is displayed in Check in Page
As a user
I want to verify that I can see Upgrade to Business Select Fare button and message

Scenario: Validate displaying Upgrade to Business Select Fare in Check in Page for Segments without EB

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
    |isValidForCheckin|true|
    |earlyBirdPurchased|false|

When I search and book a flight to be used for checkin, change or cancel flows from the search flights page
And I retrieve my reservation to checkin
And I click on upgrade to business select
Then I see "Select Flights to Upgrade" page
And I verify that I can upgrade my segments