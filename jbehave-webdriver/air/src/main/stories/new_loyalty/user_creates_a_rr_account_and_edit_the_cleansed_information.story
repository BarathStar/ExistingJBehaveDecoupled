Loyalty - This flow verifies that when the user chooses to edit the address information that has been successfully cleansed on the Review Page, the user is redirected to the Enrollment page, populating it with the cleansed information. Then the user is able to re-initiate the Enrollment flow, going to Review page and ending in the confirmation page.

Meta:
@dyna_stubs
@flow rr
@not_live
@process create
@user anonymous

Narrative:
In order to validate the Enrollment page edition flow with cleansed information
As a user
I want to verify that I am on the Enrollment confirmation page.

Scenario: Validate the Enrollment page edition flow with cleansed information

Given I am on the Review page with my address and name information successfully cleansed
When I choose to edit my account information
Then I am redirected to the Enrollment page
And I see my address and name information cleansed on the Enrollment page
When I submit the Enrollment form filling the password fields
Then I should be redirected to the Review Page
When I confirm my account creation on the Review Page
Then I should be redirected to the Enrollment Confirmation page