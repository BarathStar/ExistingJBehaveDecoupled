AV-2233 -  Promo Code option is enabled and Senior Passenger Count option is disabled when booking Air and selecting Show Fares in Points

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
On the Book Air page, clicking on Show Fares in Points, Promo Code option should be enabled and the Senior Passenger Count option should be disabled.

Scenario: On the Book Air page, switch from Show Fares in Dollars to Points. Promo Code option should be enabled and the Senior Passenger Count option should be disabled.

Given I am on the Search Flight page
And the Senior Passenger Count field is enabled on the Air Search page
And the Promo Code field is enabled on the Air Search page
When I select the Show Fares in Points option
Then I see Senior Passenger Count field is disabled on the Air Search page
And I see Promo Code field is enabled on the Air Search page
