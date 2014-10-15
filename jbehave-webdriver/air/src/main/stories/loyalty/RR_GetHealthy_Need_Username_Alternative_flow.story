I Need My Username/Account number Flow

Meta:
@suite GetHealthy
@flow Need Username/Account number flow
@process Account Info Recovery
@user Regular Member / Regular Customer
@storyId PODVI-64 PODVI-68 PODVI-52 PODVI-53
@not_passing draft

Narrative:
In order to receive my account loggin info
As a rapid reward customer or as rapid rewards member
I want to get my Username and Account Number for one of my several accounts by completing
the I need my username/account number flow with my email information

Scenario: User recovers accounts info for multiple accounts password

Given I am on I Need My Username/Account number tab from destination finder entry point
When I complete the I Need My Username/Account form with the email option selected
Then I see the Multiple Account page
When I select an account at the Multiple Account page
Then I see the Security Questions page
When I complete the Security Question page
Then I see the username/account number confirmation page