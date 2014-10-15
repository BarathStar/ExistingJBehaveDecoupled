Verify the Trip Name Section and the option to add the Air product to a previous existing trip on Reconcile page.

Meta:
@flow air
@process change
@traveler adult
@user rr_member
@dyna_stubs

Narrative:
In order to see the Name Trip Section and the option to add the Air product to an existing trip on Reconcile Page
As a Rapid Rewards Member (with saved trips)
I want to change the Air product reservation

Scenario: Rapid Rewards Member (with saved trips) changes an Air product that is not part of a trip and views Trip Name Section and the option to add the Air product to an existing trip on Reconcile page

Given I am a Rapid Rewards Member passenger
And I have the following car itinerary data:
    |Field|Value|
    |pickUpStation|DAL|
    |dropOffStation|DAL|
    |pickUpDate|+1|
    |dropOffDate|+2|
    |carType|MidSize|
    |carVendor|Alamo|

And I have included a Car in my shopping cart
And I have finished my purchase adding my product to a new trip named My Trip
And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |departureDate|+1|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

And I have an Air product which is not part of a trip
When I login from Login page
And I select to change my entire itinerary and continue to Reconcile page
Then I see the trip section where the option to name the trip is offered by default on the Reconcile page
And I see the option to add the Air product to an existing trip on the Reconcile page