Verify the unachieved dream trip shown under "Free Flight Tracker" section

Meta:
@flow rr
@process loyalty
@user rr_member
@dyna_stubs

Narrative:
In order to see my unachieved dream trip listed on the account sidebar
As a Rapid Rewards Member (with a dream trip setup and without enough points to achieve the dream trip)
I want to check "Free Flight Tracker" section under "My Rapid Rewards" on the account sidebar

Scenario: Rapid Rewards Member (with a dream trip setup and without enough points to achieve the dream trip) views the unachieved dream trip on the account sidebar
Given I am a Rapid Rewards Member with a dream trip setup named Going on Vacations
And I DO NOT have enough points to achieve the dream trip
When I log in from the account sidebar at the Search Flight page
And I expand My Rapid Rewards section
Then I see the dream trips module on the account sidebar
And I should not see the option to check dates for the trip
And I see the name of the unachieved dream trip displayed below the title
And I see a mustard progress bar displaying my progress towards achieving that dream trip
And I see the percentage complete displayed at the end of the progress bar