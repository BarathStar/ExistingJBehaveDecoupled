Verify  the achieved dream trip shown under "Free Flight Tracker" section

Meta:
@flow rr
@process loyalty
@user rr_member
@dyna_stubs

Narrative:
In order to see my achieved dream trip listed on the account sidebar
As a Rapid Rewards Member (with a dream trip setup and enough points to achieve the dream trip)
I want to check "Free Flight Tracker" section under "My Rapid Rewards" on the account sidebar

Scenario: Rapid Rewards Member (with a dream trip setup and enough points to achieve the dream trip) views the achieved dream trip on the account sidebar
Given I am a Rapid Rewards Member with a dream trip setup named DAL - MDW
And I have enough points to achieve the dream trip
When I log in from the account sidebar at the Search Flight page
And I expand My Rapid Rewards section
Then I see the dream trips module on the account sidebar
And I see the option to check dates for the trip
And I see the name of the achieved dream trip displayed below the title
And I see a full, green progress bar representing my achievement
And I see 100% displayed at the end of the percent progress bar