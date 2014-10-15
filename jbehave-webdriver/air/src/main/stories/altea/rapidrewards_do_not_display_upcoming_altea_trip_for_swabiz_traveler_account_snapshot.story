Log in to SWABIZ traveler and verify that Account Snapshot - Upcoming trips section has no upcoming Altea itinerary.

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
I should not be seeing any international air itinerary in my upcoming trip section in account snapshot

Scenario: SWABIZ Traveler should not view the upcoming International itinerary booked through Dotcom in Account Snapshot - Upcoming trips section
Given I am a SWABIZ Traveler located in the Traveler Account Login
And I have my account setup with an associated company ID
And I have an Altea upcoming trip
When I login into SWABIZ by entering my company ID, my account number and password
Then I see my account snapshot
And I see a message indicating that there are no Upcoming Trips
