The user is not allowed to modify the trip name while changing an air reservation which belongs to a trip

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs
@project tmAir

Narrative:
In order to verify that the trip name cannot be updated
As a User
I want to retrieve and change the air reservation which belong to a trip and not be able to change the trip name

Scenario: Customer cannot change the trip's name
Given I am:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have selected the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|DAL|
    |departureDate|+1|
    |arrivalStation|AUS|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|

Given I have included an Air in my shopping cart
And I have finished my purchase adding my product to a new trip named My Trip
When I retrieve my itinerary
And I select to change my entire itinerary and continue to Reconcile page
Then I should not be able to change the name of the trip on the Reconcile Page