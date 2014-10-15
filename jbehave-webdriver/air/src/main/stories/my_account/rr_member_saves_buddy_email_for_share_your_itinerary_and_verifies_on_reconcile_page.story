RR Member tries to add a buddy name and buddy email to Communication Preferences under My Preferences.
On clicking the share Your Itinerary section in the reconcile page of the change air booking flow,
the buddy name and email which was previously added gets displayed.

Meta:
@user rr_member
@traveler adult
@dyna_stubs
@flow rr
@process loyalty
@user rr_member
@project avengers_14.8
@project_in_dev

Narrative:
In order to view the options available for buddy name and buddy email on the reconcile page,
As a Logged in RR Member
I want to login as a RR Member and add the buddy name and email in My Communication Preferences section.

Scenario: User logs in as a RR member and adds a buddy name and email in Communication Preferences section.
User tries to change a flight, and navigates till the reconcile page, and on click of the share your itinerary field,
all the previously added options are available to the user.

Given I am a Rapid Rewards Member
And I am flying a round-trip Southwest Southwest flight
And I login from Login page
When I select the preference page
And I select the Communication Preferences link
And I add a buddy name and email under Share Your Itinerary section
And I click on Update button
And I have a flight booked
And I select to change my entire itinerary and continue to Reconcile page
And I click on the Share Your Itinerary section
Then I see the buddy name being displayed