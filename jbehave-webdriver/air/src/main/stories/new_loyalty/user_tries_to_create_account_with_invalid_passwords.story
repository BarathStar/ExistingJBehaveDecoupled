Loyalty - This flow verifies that creating an account with invalid passwords fail.

Meta:
@dyna_stubs
@flow rr
@process create
@user anonymous
@project_in_dev

Narrative:
In order to create an account
As a user
I want to verify that entering invalid passwords results in an oops message.

Scenario: Create account fails with invalid passwords

Given I am an anonymous user
When I access Create an Account page
When I submit the account creation form with a password containing invalid characters
Then I see oops message for an invalid password
When I submit the account creation form with a short password
Then I see oops message for a short password
