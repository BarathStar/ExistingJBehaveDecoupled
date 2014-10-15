Log in to SWABIZ traveler and verify that View all Upcoming trips page has no upcoming Altea itinerary.

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs
@project coda
@not_live
@storyid DCAIR-7804

Narrative:
In order to reach and see my account information
As a SWABIZ Traveler and a Rapid Rewards member
I want to enter my Company ID, the account number and password and be redirected to my account since I am a user who is associated to the company
I should not be seeing any international air itinerary in View all Upcoming trips page

Scenario: SWABIZ Traveler should not view the upcoming International itinerary booked through Dotcom in View all Upcoming trips page
Given I am a SWABIZ Traveler located in the Traveler Account Login
And I have my account setup with an associated company ID
And I have a domestic upcoming trip
And I have an Altea upcoming trip next month
When I login into SWABIZ by entering my company ID, my account number and password
And I decide to view all the upcoming trips from account's Snapshot
Then I see only the domestic trip and not the international trip
