User tries to apply Travel Funds on Purchase Page when selecting only Car

Meta:
@flow car
@process booking
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
As a user who is booking a car
I do not want to be able to use travel funds as form of payment
So that I know that travel funds are not a valid form of payment when booking car reservations.

Given I have the following car itinerary data:

    |Field|Value|
    |pickUpStation|DAL|
    |dropOffStation|DAL|
    |pickUpDate|+1|
    |dropOffDate|+2|
    |carType|MidSize|
    |carVendor|Hertz|

And I am a customer on the car reservation home page
When I make a car reservation
Then I am not able to apply my travel funds on the Purchase Page