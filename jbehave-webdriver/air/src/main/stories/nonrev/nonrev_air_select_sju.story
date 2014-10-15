View SJU as available station when booking a flight in Non-Rev

Meta:
@flow air
@process booking
@user swalife
@traveler adult
@project nonrev
@storyId PODVI-1262
@dyna_stubs
@not_live
@project_in_dev

Narrative:
As a Swalife user
I want to be able to list a flight to San Juan, PR
So that I can ensure proper use of the website

Scenario: View SJU as available station when booking a flight

Given Im on the nonrev landing page
When I select SJU as origin station
And I select FLL as destination station
Then I verify that the SJU is displayed as valid origin station
