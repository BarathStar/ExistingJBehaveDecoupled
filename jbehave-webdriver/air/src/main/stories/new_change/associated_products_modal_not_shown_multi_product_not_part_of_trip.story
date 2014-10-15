Associated products modal is not shown when a multi-product reservation is not part of a trip

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs
@project tmAir

Narrative:
In order to not view Associated Products Modal in a multi-product reservation which is not part of a trip
As an Anonymous User
I want to retrieve and change my reservation

Scenario: Anonymous User does not view Associated Products Modal on Change Flow when a multi-product reservation is not part of a trip
Given I am:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have selected the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|HOU|
    |departureDate|+1|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|

And I have the following car itinerary data:
    |Field|Value|
    |pickUpStation|DAL|
    |dropOffStation|DAL|
    |pickUpDate|+1|
    |dropOffDate|+2|
    |carType|MidSize|
    |carVendor|Alamo|

And I have included an Air in my shopping cart
And I have included a Car in my shopping cart
And I have finished my purchase without adding my products to a trip
When I want to change my itinerary
And I select to change my entire itinerary
Then I should not see the Air Associated Products Modal
When I complete the changing process
Then I see that the air product just booked has no trip name provided by the user
And I should not see any references to other purchases as the Air product is not added to a trip