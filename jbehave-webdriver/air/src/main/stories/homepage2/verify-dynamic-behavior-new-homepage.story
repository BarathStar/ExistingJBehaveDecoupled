Verify the dynamic behavior of air tab on the new homepage

Meta:
@project homepage2
@flow air
@dyna_stubs
@homepage2on


Narrative:
In order to verify the dynamic behavior of new Homepage
As a customer
I want to click on Air, Car, Hotel and Vacations tab and verify the behavior of each tab

Scenario: Verifying dynamic behavior for all the tabs on new homepage

Given I am on the Homepage
Then I verify the Air tab
And I verify the Hotel tab
And I verify the Car tab
And I verify the Vacations tab