AV-2233 - Promo Code option is enabled and Senior Passenger Count option is disabled when booking Air and selecting Show Fares in Points in Booking widget

Meta:
@flow air
@process booking
@user anonymous
@passenger adult
@dyna_stubs
@project avengers_13.10
@project_in_dev
@not_live

Narrative:
On the Booking widget, clicking on Show Fares in Points, Promo Code option should be enabled and the Senior Passenger Count option should be disabled.

Scenario: In the Booking widget, switch from Show Fares in Dollars to Points. Promo Code option should be enabled and the Senior Passenger Count option should be disabled.

Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
And I select book a flight
And the Senior Passenger Count field is enabled on the Book A Trip Modal
And the Promo Code field is enabled on the Book A Trip Modal
When I click on points in the Book A Trip Modal
Then I see Senior Passenger Count field is disabled on the Book A Trip Modal
And I see Promo Code field is enabled on the Book A Trip Modal
