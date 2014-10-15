I Need My Password Flow

Meta:
@suite GetHealthy
@flow Need Password Flow
@process Reset Password for Multple Accounts
@user Regular Member / Regular Customer
@storyId PODVI-64 PODVI-65 PODVI-52 PODVI-53
@not_passing draft

Narrative:
In order to loggin to my account
As a rapid reward customer or as rapid rewards member
I want to reset the password for one of my several accounts completing the I Need My Password flow
with my account information

Scenario: User resets a multiple accounts password

Given I am on I Need My Password tab from account bar entry point
When I complete the I need My Password form with the email option selected
Then I see the Multiple Account page
When I select an account at Multiple Account page
Then I see the Security Questions page
When I complete the Security Question page
Then I see the password reset confirmation page
