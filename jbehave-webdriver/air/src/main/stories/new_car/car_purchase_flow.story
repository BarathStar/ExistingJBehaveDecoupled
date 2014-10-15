Reserve a car for an adult

Meta:
@flow car
@process reservation
@user anonymous
@traveler adult
@dyna_stubs
@not_passing draft

Narrative:
In order to be able to drive when I get to my destination
As an adult
I want to reserve a car

Scenario: A user goes to southwest website and directly tries to reserve car
Given I want to see the accordion purchase page
And I have the following car itinerary data:
    |Field|Value|
    |pickUpStation|DAL|
    |dropOffStation|DAL|
    |pickUpDate|+1|
    |dropOffDate|+2|
    |carType|MidSize|
    |carVendor|Budget|

And I am a customer on the car reservation home page
And I have a car selected and continue to the purchase page
When I enter the driver and payment information AND Purchase
Then I should see a car confirmation number
