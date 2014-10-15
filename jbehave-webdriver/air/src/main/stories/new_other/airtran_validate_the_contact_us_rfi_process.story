This flow verifies that when the user is required to provide more information for Contact Us process on AirTran, the user is able to complete additional information and submit it.

Meta:
@flow other
@process contact us
@user adult
@traveler anonymous
@dyna_stubs
@not_live

Narrative:
In order to validate the Contact Us - Request For Information page
As a user
I want to verify that I am on the Thanks you page.

Scenario: Validate the AirTran Contact Us Page-Request For Information (RFI) Process.

Given I am a User on the Airtran RFI Page
When I submit an empty form on Airtran RFI Page
Then I receive an error message stating that Additional Information Was Not Entered
When I enter the additional information in the form
And I submit form on Airtran RFI Page
Then I see a Thank You message confirming receipt of the additional information