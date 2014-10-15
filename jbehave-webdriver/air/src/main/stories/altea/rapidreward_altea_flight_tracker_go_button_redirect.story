Verify from the Dream Trip Detail page that GO button redirects to Altea

Meta:
@flow air
@process booking
@user rr_member
@traveler adult
@project coda
@dyna_stubs
@not_live
@project_in_dev
@storyid OPS-1257

Narrative: In order to select optimal dates for Southwest international points travel
As a RR member
I want to be able to see the points costs on a calendar grid for my selected Southwest International outbound/inbound stations from my Rapid Rewards Free Flight Tracker Detail page

Scenario: Redirect Check Dates for a saved Free Flight Tracker on an International flight from My Rapid Rewards Free Flight Tracker Detail

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
And I access the My Rapid Rewards account page
And I click dream trip link My International Vacation
And I click GO button in dream trip details
Then I see the Altea Low Fare Calendar for HOU - CUN
