Verify that the hotel data is retained on the new homepage

Meta:
@project homepage2
@flow hotel
@dyna_stubs
@homepage2on


Narrative:
In order to verify the data retention in Hotel tab
As a Customer
I want to enter data in Hotel tab and switch to Car or Air or Vacations Tab and come back to Hotel tab


Scenario: Verifying data retention for Hotel tab on new homepage

Given I am on the Homepage
Then I verify that the data is retained in Hotel tab
