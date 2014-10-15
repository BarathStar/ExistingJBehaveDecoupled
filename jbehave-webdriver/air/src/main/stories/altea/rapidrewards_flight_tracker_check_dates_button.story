Verify from the My Rapid Rewards page a Flight Tracker's Check Dates button redirects to the Domestic Low Fare Calendar

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
I want to see estimated points costs on a calendar grid for my selected Southwest domestic stations from a free flight tracker
So that I can choose optimal dates for Southwest domestic points travel

Scenario: Rapid Rewards Member (with a Flight Tracker setup) on the My Rapid Rewards page clicks on Check Dates from a Flight Tracker
Given I am a Rapid Rewards Member with a dream trip setup named My Domestic Vacation
And I DO NOT have enough points to achieve the dream trip
When I log in from the account sidebar at the Search Flight page
And I access the My Rapid Rewards account page
And I click the Flight Tracker Check Dates button
Then I view the Low Fare Calendar page