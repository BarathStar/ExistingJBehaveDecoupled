Verify from the Dream Trip Detail page that GO button redirects to Lowfare Calendar

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

Narrative: In order to select optimal dates for Southwest points travel
As a RR member
I want to be able to see the points costs on a calendar grid for my selected Southwest outbound/inbound stations from my Rapid Rewards Free Flight Tracker Detail page

Scenario: Redirect Check Dates for a saved Free Flight Tracker on Soutwest flight from My Rapid Rewards Free Flight Tracker Detail

Given I am a Rapid Rewards Member with a dream trip setup named My Domestic Vacation
And I have enough points to achieve the dream trip
When I log in from the account sidebar at the Search Flight page
And I access the My Rapid Rewards account page
And I click dream trip link My Domestic Vacation
And I click GO button in dream trip details
Then I view the Low Fare Calendar page