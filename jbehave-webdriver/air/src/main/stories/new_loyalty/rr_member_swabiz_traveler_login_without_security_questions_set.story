Verify successful submission of security questions and email preferences on Update Account page for SWABIZ Traveler

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs
@not_live
@project avengers_13.10
@project_in_dev
@storyId AV-2307/AV-2312/AV-2339
@passenger adult

Narrative:
In order to verify successful submission of security questions and email preferences
As a SWABIZ Traveler
I want to complete and submit the fields on the Update Account page and continue to see My Account Snapshot page

Scenario: SWABIZ Traveler reaches My Account Snapshot page upon successful submission of security questions and email preferences on Update Account page

Given I am a Rapid Rewards Member without security questions
And I am a SWABIZ Traveler located in the Traveler Account Login
When I login into SWABIZ by entering my company ID, my account number and password
And I complete the Update Your Rapid Rewards Account page
And I confirm my association with the company
Then I see the My Account Snapshot page
