Log in to swabiz traveler and verify the Account Snapshot page.

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
In order to reach and see my account information
As a SWABIZ Traveler
I want to enter my Company ID, the account number and password and be redirected to my account since I am a user who is associated to the company

Scenario: SWABIZ Traveler views the account information immediately after logging-in because he/she is associated to a company
Given I am a SWABIZ Traveler located in the Traveler Account Login
And I have my account setup with an associated company ID
When I login into SWABIZ by entering my company ID, my account number and password
Then I see my account snapshot
