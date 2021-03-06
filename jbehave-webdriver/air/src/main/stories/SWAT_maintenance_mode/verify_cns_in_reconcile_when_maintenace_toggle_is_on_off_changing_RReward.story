Verify that cns is allow or not in purchase depend on the RREdit maintenance toggle status when changing a flight and logged in rapid reward account

Meta:
@flow air
@process change
@user rr
@passenger adult
@project SWAT_Maintenance
@not_live
@dyna_stubs
@project_in_dev

Narrative:
In order to fly on a date that I want
As an adult
I want to see the click and save option in the reconcile page when change a flight

Scenario: Verify that cns is not allowed in purchase with RREdit maintenance toggle status when changing a flight and logged in rapid reward account

Given I have the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|DAL|
    |arrivalStation|HOU|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
And I am logged in as a valid Rapid Rewards member on the Search Flights page
And I have a flight booked
When I select to change my entire itinerary and continue to Reconcile page
Then I should not see Click And Save option