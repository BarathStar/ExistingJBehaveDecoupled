Verify that after deleting the custom primary IRN, the following one is set by default as primary IRN

Meta:
@flow rr
@process IRNs
@user customer
@passenger adult
@dyna_stubs
@not_live
@project_in_dev
@project SWAT
@storyId SWAT-2855

Narrative:
As a Traveler
In order to verify that another IRN is set as primary by default when the current primary is deleted
So that

Scenario: After deleting the custom primary IRN, the following one is set by default as primary IRN

Given I am a SWABIZ Traveler located in the Traveler Account Login
And I have my RR account setup with an associated company ID
And I login into SWABIZ by entering my company ID, my account number and password
And I select the link My Preferences
And I have chosen to navigate the Payment Information link
When I delete the Primary IRN
Then I see that a custom IRN was set as a Primary IRN
