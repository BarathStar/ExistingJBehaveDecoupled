SWABIZ - BUG-3050 - [Check Flight Status] Oops error message on Check flight status Page

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@story_id BUG-3050
@dyna_stubs
@not_live

Narrative:
After logging in as a traveler and viewing the check flight status results page, the results page should remain visible after logout

Scenario:
After logging in, check the status of a flight and logout. The status results page should remain visible.

Given I am a SWABIZ Traveler located in the Traveler Account Login
And I have my RR account setup with an associated company ID
And I login into SWABIZ by entering my company ID, my account number and password
And I am flying from SJU to AUS
And I am on the Check Flight Status page
When I search for the flight status
Then I should see itineraries matching my search on the flight status page
When I log out from my Rapid Reward Account
Then I should see itineraries matching my search on the flight status page