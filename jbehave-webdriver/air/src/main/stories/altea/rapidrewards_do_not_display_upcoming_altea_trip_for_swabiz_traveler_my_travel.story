Log in to SWABIZ traveler and verify that My Travel - Upcoming trips section has no upcoming Altea itinerary.

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
I should not be seeing any international air itinerary on my upcoming trip section in My Travel - Upcoming trips section

Scenario: SWABIZ Traveler should not view the upcoming International itinerary booked through Dotcom in My Travel - Upcoming trips section
Given I am a SWABIZ Traveler located in the Traveler Account Login
And I have my account setup with an associated company ID
And I have an Altea upcoming trip
And I login into SWABIZ by entering my company ID, my account number and password
When I select My Travel link
Then I see a message indicating that there are no Upcoming Trips in My Travel Snapshot page
