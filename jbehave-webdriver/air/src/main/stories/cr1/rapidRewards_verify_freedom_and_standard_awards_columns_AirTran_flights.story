Rapid Rewards Member with Freedom and Standard Awards searches for flights

Meta:
@flow air
@project CR-1
@process booking
@user rr_member
@traveler adult
@dyna_stubs
@storyId DCQA-200, ZR-894, ZR-986
@project_in_dev

Narrative:
As a Rapid Rewards Member with both Freedom and Standard Awards
I want to log in and search for an Airtran flight using awards from book a trip modal
So that I see the Standard and Freedom Awards columns on the select flights page.

Scenario: Search for AirTran flights using awards, then verify columns for Freedom and Standard Awards redemption

Given I am a Rapid Rewards member with both a Freedom and Standard Award
And I login from Login page
When I search Airtran flights using my awards
Then I see the Standard and Freedom Awards Columns in the Select Flights columns
