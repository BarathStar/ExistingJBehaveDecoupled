I Need My Password Flow

Meta:
@suite GetHealthy
@flow Need Password Flow
@process Reset Password
@user Regular Member / Regular Customer
@storyId PODVI-64 PODVI-52 PODVI-53
@not_passing draft

Narrative:
In order to loggin to my account
As a rapid reward customer or as rapid rewards member
I want to reset my password completing the I Need My Password flow with my account information
by email address

Scenario: User resets accounts password

Given I am on I Need My Password tab from destination finder entry point
When I complete the I need My Password form with the email option selected
Then I see the Security Questions page
When I complete the Security Question page
Then I see the password reset confirmation page