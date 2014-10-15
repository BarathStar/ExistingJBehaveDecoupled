Purchase A Rapid Rewards Air Ticket with a transitional award and verify the flight purchase

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@user rr_member
@traveler adult
@dyna_stubs
@storyId DCQA-63, ZR-894, ZR-987

Narrative:
As a rapid rewards member
I want to book a Round trip Airtran flight using a transitional award
So that I may view the flight confirmation.

Scenario: Rapid Rewards Member purchases air with a transitional award, and verify air purchase

Given I am a Rapid Rewards Member with a Transitional Award
And I login from Login page
When I search Airtran flights using my awards
And I select flights and finish booking using a standard award
Then I receive an air confirmation number
