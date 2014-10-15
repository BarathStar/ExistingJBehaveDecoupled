AirTran RoundTrip Points Search

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@user rr_member
@traveler adult
@storyId DCQA-200, ZR-894
@dyna_stubs
@not_passing broken

Narrative:
As a customer logged into R.R account with promocerts
I want to select one-way airtran flight through book a tri modal
So that I see the certificate radio button is selected on select flights page.


Scenario: Search for AirTran flights in dollar, then use a modified search in points

Given I am flying a one-way AirTran flight
And I am logged in as a valid Rapid Rewards member on the Search Flights page
When I click my account link
And I search flights using a promotional certificate
Then I see the Certificate radio button selected to redeem a promotion
