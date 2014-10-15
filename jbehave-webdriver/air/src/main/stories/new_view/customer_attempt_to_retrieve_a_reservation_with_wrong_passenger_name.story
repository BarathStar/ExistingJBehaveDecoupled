Attempt to retrieve an Itinerary with a valid PNR but wrong passenger

Meta:
@flow air
@process view
@user anonymous
@traveler adult
@project view_all_pnrs
@dyna_stubs

Narrative:
In order to see the OOPS message
As an anonymous user
I attempt to retrieve an Itinerary using a valid PNR but a wrong passenger
on the View/Share Itinerary page

Scenario: Customer tries to retrieve an Itinerary with a valid PNR but wrong passenger

Given I am flying a round-trip Southwest Southwest flight
And I have booked an Air product on a trip named My Trip
When I attempt to retrieve my itinerary with a wrong passenger
Then I view the OOPS message for the invalid PNR on the View/Share Itinerary page