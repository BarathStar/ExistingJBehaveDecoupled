This flow verifies that when the user has upgradeable segments and have EB for all of them, the user does not see the buttons and messages available to update to business select, when is checking in.

Meta:
@flow air
@process checkin
@user adult
@traveler anonymous
@dyna_stubs
@not_live

Narrative:
In order to validate that Upgrade to Business Select Fare option is not displayed in Check in Page
As a user
I want to verify that I do not see Upgrade to Business Select Fare button and message when I have EB.

Scenario: Validate not displaying Upgrade to Business Select Fare in Check in Page for Segments with EB.

Given I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |arrivalStation|DAL|
    |returnDate|+5|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|
    |isValidForCheckin|true|
    |earlyBirdPurchased|true|
    |earlyBird|true|

When I search and book a flight to be used for checkin, change or cancel flows from the search flights page
And I retrieve my reservation to checkin
Then I don't see a button to upgrade neither any message informing me that I am able to upgrade to business select