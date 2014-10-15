Injecting hibernate exception in the car purchase flow

Meta:
@suite faultInjectionServiceTier
@flow car
@process reservation
@user anonymous
@not_passing draft

Narrative:
As a system professional
I want to inject a hibernate exception
In order to ensure the proper error handling is invoked for car reservation

Scenario: A user goes to southwest website and directly tries to reserve car
Given I have the following car itinerary data:
    |Field|Value|
    |pickUpStation|DAL|
    |dropOffStation|DAL|
    |pickUpDate|+1|
    |dropOffDate|+2|
    |carType|MidSize|
    |carVendor|Budget|

And I am a customer on the car reservation home page
And I have a car selected and continue to the purchase page
And I am injecting a ./src/main/stories/fault_injection/btm/CarReservation_hibernateException.btm fault
When I enter the driver and payment information AND Purchase
Then I should see a car confirmation number
And I unload all byteman rules
