Verify special rate messages in car search page with Hertz company as an adult passenger

Meta:
@flow car
@process booking
@user anonymous
@passenger adult
@dyna_stubs
@not_live

Narrative:
As an adult
In order to search for a Hertz car
I want to verify that special rate messages are shown when I search with promo or corp ID codes
So that

Scenario: verify that special rate message is shown above the price for all the Hertz available cars


Given I have the following car itinerary data:

    |Field|Value|
    |pickUpStation|HOU|
    |dropOffStation|HOU|
    |pickUpDate|+1|
    |dropOffDate|+2|
    |carType|MidSize|
    |carVendor|Hertz|
    |promoCode|1234|
    |promoType|Promotion Code|

And I am a customer searching for cars from the cars search page
When I enter a promo code for Hertz
And I continue to select cars page
Then I see the special rate message above the price for all the Hertz available cars
