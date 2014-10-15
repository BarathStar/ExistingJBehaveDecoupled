Verify from the My Rapid Rewards snapshot page a Track Your Trip link goes to View All Flight Trackers

Meta:
@flow air
@process booking
@user rr_member
@traveler adult
@project JL_International_WP112
@dyna_stubs
@not_passing draft
@not_live
@project_in_dev
@storyid OPS-1240


Narrative:
As a Rapid Rewards Customer viewing the Rapid Rewards Snapshot page
I want to see the Track Your Trip link
So that I can click and go to the View All Flight Trackers page

Scenario: Rapid Rewards Member (with a Flight Tracker setup) on the My Rapid Rewards page clicks Track Your Trip link and views all Flight Trackers
Given I am a Rapid Rewards Member with a dream trip setup named My Domestic Vacation
And I DO NOT have enough points to achieve the dream trip
When I log in from the account sidebar at the Search Flight page
And I view the Snapshot page
And I click the Track Your Trip link
Then I view the Reward Flight Trackers page
