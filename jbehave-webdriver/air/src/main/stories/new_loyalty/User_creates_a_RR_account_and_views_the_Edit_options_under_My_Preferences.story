Create a Rapid Rewards account, and verify Edit options for "Username & Password", "Contact Information" and "Communication Preferences" sections under "My Preferences".

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
As an Anonymous User
I want to create an account with Travel Guide Profile from Create a Travel Guide page
In order to create a Travel Guide Profile without enrolling to the Rapid Rewards Program

Scenario: User creates a Rapid Rewards account and views the Edit options under "My Preferences" for "Username & Password", "Contact Information" and "Communication Preferences" sections
Given I am a Southwest User at "Create an Account" page
When I complete all the mandatory fields
And I select to subscribe to Click N Save email option
And I select to subscribe to In a Nutshell email option
And I select to create a Rapid Rewards account
And I select to subscribe to The Rapid Rewards Report email option
And I select to subscribe to The Rapid Rewards E-mail Update email option
And I confirm the account creation
And I decide to complete my account's Preferences
Then I see the "Username & Password" page is not editable
And I see the Username & Password section can be edited
When I access my account's Contact Information
Then I see the Contact Information page is not editable
And I see the Contact Information section can be edited
When I access my account's Communication Preferences
Then I see the Communication Preferences page is not editable
And I see I'm subscribed to all the Email options with the email address used at account creation time
And I see the Communication Preferences section can be edited
