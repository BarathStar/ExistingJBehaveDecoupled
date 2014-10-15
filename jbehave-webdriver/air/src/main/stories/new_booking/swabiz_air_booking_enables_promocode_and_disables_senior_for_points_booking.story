SWABIZ - AV-2233 - Promo Code option is enabled and Senior Passenger Count option is disabled when booking Air and selecting Show Fares in Points

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

Given I am a SWABIZ Traveler located in the Traveler Account Login
And I have my RR account setup with an associated company ID
And I login into SWABIZ by entering my company ID, my account number and password
And I select the Book Travel tab
And the Senior Passenger Count option is enabled
And the Promo Code option is enabled
When I select the Show Fares in Points option
Then I see the Senior Passenger Count Option is disabled
And I see the Promo Code option is enabled
