The user is able to indicate a trip name to an Air product reservation which is not part of a trip

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs
@not_passing broken
@project tmAir

Narrative:
In order to add a trip name to an Air product reservation which is not part of a trip
As an Anonymous User
I want to retrieve and change my reservation including the trip name

Scenario: Anonymous User adds a trip name to an Air reservation which is not part of a trip
Given I am:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have selected the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|DAL|
    |departureDate|+1|
    |arrivalStation|ABQ|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|

And I have included an Air in my shopping cart
And I have finished my purchase without adding my product to a trip
When I retrieve the Air reservation to change it
And I select to change my entire itinerary and continue to Reconcile page
Then I am able to add a trip name to the Air product reservation on the Reconcile Page