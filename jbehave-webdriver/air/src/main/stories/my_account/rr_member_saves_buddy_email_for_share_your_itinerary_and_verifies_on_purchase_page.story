RR Member tries to add a buddy name and buddy email to Communication Preferences under My Preferences.
On clicking the share Your Itinerary section in the purchase page of the new air booking flow,
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
In order to view the options available for buddy name and buddy email on the purchase page,
As a Logged in RR Member
I want to login as a RR Member and add the buddy name and email in My Communication Preferences section.

Scenario: User logs in as a RR member and adds a buddy name and email in Communication Preferences section.
User tries to book a new flight, and navigates till the purchase page, and on click of the share your itinerary field,
all the previously added options are available to the user.

Given I am a Rapid Rewards Member
And I am flying a round-trip Southwest Southwest flight
And I login from Login page
When I select the preference page
And I select the Communication Preferences link
And I add a buddy name and email under Share Your Itinerary section
And I click on Update button
And I search and select flights and continue to the Purchase page
And I click on the Share Your Itinerary section
Then I see the buddy name being displayed