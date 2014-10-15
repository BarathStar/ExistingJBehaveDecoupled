Verify that fares are discounted for change flow in the select flight page when a Website Wide Promo for dollar is active on SWABiz

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
I want change a reservation on SWABiz when Website Wide Promo for dollar is active
So that I can see discounted dollar fares are available for purchase

Scenario: Verify the discounted dollar fare is seen in the select flights page for Website Wide Promo on SWABiz

Given I am flying a round-trip Southwest Southwest flight
And I am on the swabiz home page
And I have anonymously logged into a SWABiz account
And I have a flight booked for a SWABiz Anonymous user
When I retrieve my reservation for change
And Website wide promo WEBSITEWIDESWABIZ is enabled
And I select to change my entire itinerary and continue to the Select New Flight page
Then I see strike through for original dollar fare
And I do not see an oops message on the Select Flights Page