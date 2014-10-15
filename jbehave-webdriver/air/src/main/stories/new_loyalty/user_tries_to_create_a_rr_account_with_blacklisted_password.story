Loyalty - This flow verifies that creating an account blacklisted password fails.

Meta:
@dyna_stubs
@flow rr
@process create
@user anonymous
@project_in_dev

Narrative:
In order to create an account
As a user
I want to verify that entering a blacklisted password results in an oops message.

Scenario: Create account fails with a blacklisted password

Given I am an anonymous user
When I access Create an Account page
And I submit the account creation form with a blacklisted password qwerty
Then I see oops message for blacklisted password
