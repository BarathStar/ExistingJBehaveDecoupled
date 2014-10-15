RR Member tries to add a preferred method of contact to Communication Preferences under My Preferences.
In the reconcile page of the change air booking flow,the preferred method of contact which was previously added in my preferences section gets displayed.
This preferred method of contact will allow us to inform you about a flight change or a delay by automated voice, SMS (text) or e-mail.

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs
@project avengers_14.8
@project_in_dev


Narrative:
As a Southwest User who is currently logged in, updates the preferred method of contact in Communcation Preferences page under My Preferences Section
and verify whether its prepopulated in reconcile flow.

Scenario: User logs in as a RR member and saves call me as preferred method of contact in Communication Preferences section.
User changes flight , and continue to air confirmation, and on click on change my itinerary and continue to reconcile
page and verify the how can we contact you section, all the added options are available to the user.


Given I am a Rapid Rewards Member
And I am flying a round-trip Southwest Southwest flight
And I login from Login page
When I select the preference page
And I select the Communication Preferences link
And I save Call Me as preferred method of contact
And I click on Update button
And I have a flight booked
And I select to change my entire itinerary and continue to Reconcile page
Then I see How can we contact you section in purchase page
