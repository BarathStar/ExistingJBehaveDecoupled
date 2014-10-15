This story verifies that in case the fields have not been completed each field will display each corresponding error message.

Meta:
@flow other
@process Contact Us
@user Anonymous
@traveler Adult
@dyna_stubs

Narrative:
In order to validate the error messages on Airtran Contact Us Page
As a user
I want to verify that I get all the corresponding error messages.

Scenario: User Submits Empty Form in Airtran Contact Us Page

Given I am a User on the Airtran Contact Us Page
When I submit an empty form on Airtran Contact Us Page
Then I should not see the Thank you! modal
And I will see the corresponding error messages for contact us Page
