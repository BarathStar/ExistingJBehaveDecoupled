IRNs list is displayed in alphabetical/numerical order on preference page as a traveler

Meta:
@flow rr
@process IRNs
@user customer
@passenger adult
@dyna_stubs
@not_live
@project SWAT
@storyId SWAT-1315

Narrative:
As a traveler
In order to do change the primary IRN
I want to watch the IRNs list displayed in alphabetical/numerical order.
So that

Scenario:  Verify that IRNs list is displayed in alphabetical/numerical order
Given I am a SWABIZ Traveler located in the Traveler Account Login
And I have my RR account setup with an associated company ID
When I login into SWABIZ by entering my company ID, my account number and password
And I select the link My Preferences
And I select the Payment Information link
Then I see the IRNs list in alphabetical/numerical order