Verify changing a booking air ticket the 'How Can We Get A Hold Of You' field is optional on the Reconcile page as an adult

Meta:
@flow air
@process change
@user anonymous
@passenger adult
@dyna_stubs

Narrative:
As an adult
In order to do a change of a previous booked air
I want to verify that 'How Can We Get A Hold Of You' field is optional on the Reconcile page
So that


Scenario: verify if 'How Can We Get A Hold Of You' field is optional on a change


Given I have the following itinerary data:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|DAL|
    |arrivalStation|HOU|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|

And I have a flight booked
When I select to change my entire itinerary and continue to Reconcile page
Then I should not see the red asterisk inside the contact info area
When I only fill payment information and confirm on the Reconcile page
Then I should see the itinerary change confirmation page