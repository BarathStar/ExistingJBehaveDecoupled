Verify that fares are discounted in the select flight page during change flow when an Embedded Promo Code is active

Meta:
@flow air
@process change
@user anonymous
@passenger adult
@dyna_stubs
@project avengers_14.2
@project_in_dev
@not_live

Narrative:
As an adult
I want to change my reservation on Swabiz when Embedded Promo Code is active for my company id.
So that I can see discounted dollar fares are available for purchase

Scenario: Verify the discounted dollar fare is seen in the select flight page for Embedded Promo

Given I am flying a round-trip Southwest Southwest flight
And I work for a SwaBiz company 99067846
And I have anonymously logged into a SWABiz account
And I am on the swabiz home page
And I have a flight booked for a SWABiz Anonymous user
When I retrieve my reservation for change
And I select to change my entire itinerary and continue to the Select New Flight page
Then I see strike through for original dollar fare
And I do not see an oops message on the Select Flights Page
