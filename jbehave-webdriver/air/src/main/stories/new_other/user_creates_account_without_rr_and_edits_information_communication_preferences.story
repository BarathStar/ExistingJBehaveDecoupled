Create an account without enrolling to the Rapid Rewards program and without Travel Guide Profile and edit the Communication Preferences options, so that I can change the email updates and news chosen in the first place when creating my account.

Meta:
@flow other
@user anonymous
@dyna_stubs

Narrative:
As an Anonymous User
I want to create an account (without Rapid Rewards enrollment) and change the Communication Preferences originally provided
In order to edit my Communication Preferences

Scenario: User creates an account (without Rapid Rewards enrollment) and edits the information in the "Communication Preferences" section
Given I am a Southwest User at "Create an Account" page

When I complete all the mandatory fields
And I select to subscribe to Click N Save email option
And I select to subscribe to In a Nutshell email option
And I select not to create a Rapid Rewards account
And I confirm the account creation
And I decide to update my account's information
And I access my account's Communication Preferences
Then I see I'm subscribed to Click 'n Save E-mail Updates with the email address used at account creation time
And I see I'm subscribed to Southwest In a Nutshell Email Updates with the email address used at account creation time
When I select to see the edit mode for my Communication Preferences
And I enroll to the Rapid Rewards program from the Communication Preferences page
And I decide to edit my account's Preferences from the Upgrade Account Confirmation page
And I access my account's Communication Preferences
Then I see I'm subscribed to all the Email options with the email address used at account creation time
