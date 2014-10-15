This flow verifies that when the toggle is on but the ACS drawers' business rules for upsell are not met, the upsell drawer isn't displayed to user.

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@removedFromAirbooking
@dyna_stubs

Narrative:
In order to validate that toggle is working properly
As a user
I want to verify that when I select a flight that doesn't met the ACS drawers' business rules for upsell, I will not see the upsell drawer

Scenario: Validate upsell drawer is not being displayed when ACS drawers' business rules for upsell are not met.
Given I have the following itinerary data:

        |Field|Value|
        |itineraryType|One Way|
        |departureStation|DAL|
        |arrivalStation|AUS|
        |departingFlight_carrierCode|WN|
        |departingFlight_fareClass|BusinessSelect|

When I search and select my flight
Then I verify ACS drawer is not being displayed