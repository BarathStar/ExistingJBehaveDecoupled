Verify that click and save  is allow or not in purchase depend on the RREdit maintenance toggle status in changing process

Meta:
@flow air
@process change
@user anonymous
@passenger adult
@project SWAT_Maintenance
@not_live
@dyna_stubs
@project_in_dev

Narrative:
In order to fly on a date that I want
As an adult traveling with a senior
I want to see the click and save option in the reconcile page when change a flight

Scenario: verify that cns is not allowed in purchase with RREdit maintenance toggle status

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