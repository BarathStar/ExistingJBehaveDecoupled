Verify from the My Rapid Rewards page an Altea Flight Tracker's Check Dates button redirects to Altea

Meta:
@flow air
@process booking
@user rr_member
@traveler adult
@project coda
@dyna_stubs
@not_live
@project_in_dev
@storyid OPS-1240


Narrative:
As a Rapid Rewards Customer
I want to see estimated points costs on a calendar grid for my selected Southwest international stations from an international flight tracker
So that I can choose optimal dates for Southwest international points travel

Scenario: Rapid Rewards Member (with a Flight Tracker setup) on the My Rapid Rewards page clicks on Check Dates from an Altea Flight Tracker
Given I am a Rapid Rewards Member with an Altea dream trip setup named My International Vacation
And I DO NOT have enough points to achieve the dream trip
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
And I access the My Rapid Rewards account page
And I view all Flight Trackers
And I click the Flight Tracker Check Dates button
Then I see the Altea Low Fare Calendar
