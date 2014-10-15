Verify that after clicking on "View Details" from a car rental option on confirmation page is redireted to the car price page

Meta:
@flow car
@process view
@user anonimous
@traveler adult
@project SWAT
@dyna_stubs
@not_live
@project_in_dev

Narrative:
As an adult
I want to view details of a car rental option after buying a flight
So that I reach to the car price page

Scenario: select a car in rental car option on confirmation page and being properly redirected to car price page

Given I am flying a round-trip Southwest Southwest flight
And I am on the purchase page
And I filled in the Purchase form and submit
When I click on 'View Details' on the car rental option
Then I should be redirected to the car price page