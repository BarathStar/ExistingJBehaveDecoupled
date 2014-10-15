Verify check dates button under the achieved altea dream trip shown in account sidebar redirects to altea

Meta:
@flow rr
@process loyalty
@user rr_member
@dyna_stubs
@not_live
@project coda
@project_in_dev
@storyid OPS-1240

Narrative:
In order to see my achieved dream trip listed on the account sidebar
As a Rapid Rewards Member (with an altea dream trip setup and enough points to achieve the altea dream trip)
I want to see the points costs on a calendar grid for any of my selected outbound/inbound stations that I have saved in altea Free Flight Tracker

Scenario: Rapid Rewards Member (with a dream trip setup and enough points to achieve the dream trip) views the achieved dream trip on the account sidebar
Given I am a Rapid Rewards Member with an Altea dream trip setup named My International Vacation
And I have enough points to achieve the dream trip
And The following routes are available:
    |Field|Value|
    |originStation|HOU|
    |destinationStation|CUN|
    |carrierDates|WN:F:[today,today+90]
And The following routes are available:
    |Field|Value|
    |originStation|CUN|
    |destinationStation|HOU|
    |carrierDates|WN:F:[today,today+90]

When I log in from the account sidebar at the Search Flight page
And I expand My Rapid Rewards section
Then I see the dream trips module on the account sidebar
And I see 100% displayed at the end of the percent progress bar
And I see the option to check dates for the trip
When I click the Flight Tracker Check Dates button
Then I see the Altea Low Fare Calendar
