Log in and confirm the company association, so that I can reach and see my account in SWABIZ.

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
In order to associate with a company enrolled in SWABIZ
As a SWABIZ Traveler
I want to enter the Company ID, the account number and password, be asked if I want to associate to the company and see the information of my account after confirming my association

Scenario: SWABIZ Traveler associates with a company and views his/her account
Given I am a SWABIZ Traveler located in the Traveler Account Login
And I have my account setup with no associated company ID
When I login into SWABIZ by entering my company ID, my account number and password
Then I see a page which requires the association with the company
When I confirm my association with the company
Then I see my account snapshot
