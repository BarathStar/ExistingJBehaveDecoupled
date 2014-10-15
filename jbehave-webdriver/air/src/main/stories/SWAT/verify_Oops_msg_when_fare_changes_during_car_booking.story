Verify oops message when the fare changes during a car booking

Meta:
@flow car
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@project SWAT
@project_in_dev
@not_live
@storyId SWAT-2935

Narrative:
In order to verify an oops message when the fare changes before completing the booking
As a anonymous user
I want to book a car

Scenario: User tries to book a car when the fare changes before completing the booking
Given I am a customer on the car reservation home page
And I have the following car itinerary data:
    |Field|Value|
    |pickUpStation|DAL|
    |dropOffStation|DAL|
    |carType|Compact|
    |carVendor|Hertz|
And I have reached the Car Price Page
When I continue the booking when the fare has changed
Then I see an oops with the message:
 We were unable to confirm your reservation at the original rate that you were quoted