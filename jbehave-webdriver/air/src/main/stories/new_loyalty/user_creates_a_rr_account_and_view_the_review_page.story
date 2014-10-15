Loyalty - This flow verifies that after properly filling the Enrollment form on Creating an Account page the user is redirected to the new Review page from which they finally confirm the account creation, ending in the confirmation page.

Meta:
@dyna_stubs
@flow rr
@process create
@user anonymous

Narrative:
In order to validate the updated Enrollment flow
As a user
I want to verify that I am on the Enrollment confirmation page .

Scenario: Validate the updated Enrollment flow

Given I am an anonymous user
When I access Create an Account page
Then I see that the text of the submit button is Continue
When I submit the Enrollment form that has been properly filled
Then I should be redirected to the Review Page
When I confirm my account creation on the Review Page
Then I should be redirected to the Enrollment Confirmation page