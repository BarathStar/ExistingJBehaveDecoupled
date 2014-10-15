I Never Logged In Before Flow

Meta:
@suite GetHealthy
@flow Never Logged In Before
@process Never Logged In Before
@user Regular Member / Regular Customer
@storyId PODVI-44 PODVI-66 PODVI-52 PODVI-53
@not_passing draft

Narrative:
In order to log in for the first time
As a rapid reward customer or as rapid rewards member who has been logged in before
I want to reset my password completing I Never Logged In Before flow with my account information

Scenario: User starts the I've never logged In Before flow

Given I am on I Never Logged In Before tab from account bar entry point
When I complete the I Never Logged In Before fields
And I select reset my password option
Then I see the Security Questions page
When I complete the Security Question page
Then I see the password reset confirmation page