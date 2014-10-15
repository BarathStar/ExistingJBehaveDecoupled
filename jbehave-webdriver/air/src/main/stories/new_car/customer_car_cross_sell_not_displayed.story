Customer car cross sell not displayed on price page

Meta:
@flow car
@process reservation
@user anonymous
@traveler adult
@dyna_stubs
@not_passing MH-88 Not getting past the car search form on new grid 100% of the time (10% on old grid)


Narrative:
In order to not get prompted to reserve a car
As an adult
I do not want to see a car cross sell on the price page of my car flow

Scenario: Do not display car cross sell on the pricing page when a car is already in the shopping cart
Given I have the following car itinerary data:
    |Field|Value|
    |pickUpStation|HOU|
    |carType|MidSize|
    |carVendor|Budget|
    |agentName|Budget|

And I am a customer on the car reservation home page
When I add a car to the shopping cart
Then I should see that I am redirected to the pricing page
And I should not see the car cross sell widget
