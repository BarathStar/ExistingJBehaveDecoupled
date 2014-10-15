This flow verifies that when the toggle is on and the ACS drawers' business rules are met, the appropriate upsell drawer is displayed to user.

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
I want to verify that when I select a flight that met the ACS drawers' business rules, I will see the upsell drawer

Scenario: Validate upsell drawer displayed when ACS drawers' business rules are met.

Given I have the following itinerary data:

          |Field|Value|
          |itineraryType|One Way|
          |departureStation|DAL|
          |arrivalStation|HOU|
          |departingFlight_carrierCode|WN|
          |arrivingFlight_carrierCode|WN|

When I select an AT flight eligible for upselling on the select flights page with dollars
Then I see the Aggressive Business Select Upsell Drawer