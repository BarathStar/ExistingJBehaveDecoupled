A Southwest User with multiple postal addresses sees only one postal address on My Account Contact Information Page.

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs
@project avengers_13.6
@project_in_dev
@storyid AV-1315

Narrative:
As a Southwest User with multiple address who is currently logged in,
I want to view my postal address in the Postal Address section under Contact Information in My Preferences page.

Scenario: A Southwest User with multiple postal addresses sees only one postal address on My Account Contact Information Page.

Given I am a Rapid Rewards Member with multiple postal addresses
And I login as a Rapid Rewards Member from Login page
When I access my account's Preferences
And I access my account's Contact Information
Then I see one Postal Address
