View an error message when a second Air product cannot be retrieved in Reservation Page as an user

Meta:
@flow air
@process view
@user anonymous
@passenger adult
@dyna_stubs
@not_live

Narrative:
In order to see an error message when an Air product cannot be retrieved
As an Anonymous User
I want to retrieve a trip product whit two PNR and the senior is not available at that moment

Scenario: Anonymous User views an error message when a second Air product cannot be retrieved

Given I have the Trip Management Database down for the Senior PNR
And I am:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|1|

And I have selected the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|DAL|
    |departureDate|+1|
    |arrivalStation|BDL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|

And I have included an Air in my shopping cart
And I have finished my purchase adding my product to a new trip named My Trip
When I retrieve my Air itinerary
And I click on the cancel link on retrieve reservation
And I confirm the air cancellation
Then I should see an error message on modal for the senior PNR