This flow verifies that after properly filling and submitting the AirTran Contact Us form the user ends in the Thank You modal page.

Meta:
@user Anonymous
@traveler Adult
@flow other
@process Contact Us
@dyna_stubs

Narrative:
In order to validate the AirTran Contact Us flow
As a user
I want to verify that I am on the Thank You modal page.

Scenario:Validate the updated AirTran Contact Us flow

Given I am a User on the Airtran Contact Us Page
When I enter a location that is not serviced by AirTran in any of the location fields
Then I see the No Airport Found message
When I fill in the required fields on Airtran
And I submit form on Airtran Contact Us Page
Then I see the Thank you! modal