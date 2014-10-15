Customer car cross sell displayed on price page

Meta:
@flow car
@process reservation
@user anonymous
@global_nav_regression
@traveler adult
@dyna_stubs

Narrative:
In order to book a add a car to my purchase
As an adult
I want to search for a car from the price page of my air booking

Scenario: Display car cross sell on the pricing page when there is no car in the shopping cart
Given I am flying a round-trip Southwest Southwest flight
And I am a customer searching for round-trip flights from the search flights page
When I add a flight to the shopping cart
Then I should see that I am redirected to the pricing page
And I should see the car cross sell widget
