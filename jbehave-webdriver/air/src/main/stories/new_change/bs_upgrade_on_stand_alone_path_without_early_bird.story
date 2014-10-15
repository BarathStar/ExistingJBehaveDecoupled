This flow verifies that when the user has upgradeable segments and does not have EB for any of them, the user can see the option to update to business select, when is in Stand Alone Path.

Meta:
@flow air
@process change
@user adult
@traveler anonymous
@dyna_stubs

Narrative:

In order to validate that Upgrade to Business Select Fare option is displayed in Stand Alone Path
As a user
I want to verify that I can see Upgrade to Business Select Fare option.

Scenario: Validate not displaying Upgrade to Business Select Fare in Stand Alone Path for Segments with EB

Given I have selected the following itinerary data:

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
    |earlyBirdPurchased|false|
    |departureDate|+5|

When I search and book a flight to be used for checkin, change or cancel flows from the search flights page
And I get to the business select page
And I retrieve my reservation through Stand Alone Path
Then I see "Select Flights to Upgrade" page
And I verify that I can upgrade my segments
