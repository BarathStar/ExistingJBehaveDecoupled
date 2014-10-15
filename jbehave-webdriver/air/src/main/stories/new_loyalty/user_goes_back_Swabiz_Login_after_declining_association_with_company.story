Log in and decline the company association, so that I am taken back to Swabiz Login page.

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
In order to associate with a company enrolled in SWABIZ
As a SWABIZ Traveler
I want to enter the Company ID, the account number and password, be asked if I want to associate to the company and go back to the SWABIZ login page when I decline the association

Scenario: SWABIZ Traveler goes back to Swabiz Login page after declining the association with the company
Given I am a SWABIZ Traveler located in the Traveler Account Login
And I have my account setup with no associated company ID
When I login into SWABIZ by entering my company ID, my account number and password
And I decline the association with the company
Then I see the Swabiz My Account Login page
