Log in using bad credentials and verify anerror message indicating that the Username/Account Number and/or Password are incorrect.

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
In order to be informed that I need to enter valid credentials to login into southwest.com
As an Anonymous User
I want to enter an invalid username and password and be shown and oops message which informs that the credentials are incorrect

Scenario: User attempts to login by entering invalid account name and password
Given I am an Anonymous User on the Account Login Page
When I attempt to access the site using an invalid account username and password
Then I see an oops message which informs that my credentials are not correct
