I Need My Username/Account number Flow - Swabiz

Meta:
@suite GetHealthy
@flow Need Username/Account number flow
@process Account Info Recovery
@user Swabiz Regular Member / Swabiz Regular Customer
@storyId PODVI-64 PODVI-68 PODVI-52 PODVI-53
@not_passing draft

Narrative:
In order to receive my account loggin info
As a rapid reward customer or as rapid rewards member
I want to get my Username and Account Number for one of my several accounts by completing
the I need my username/account number flow with my account information on swabiz site

Scenario: User recovers accounts info for multiple accounts password

Given I am on I Need My Username/Account number tab from swabiz travel tools entry point
When I complete the I Need My Username/Account number form with the account number option selected
Then I see the Multiple Account page
When I select an account at Multiple Account page
Then I see the Security Questions page
When I complete the Security Question page
Then I see the username/account number confirmation page
