Attempt to retrieve an Itinerary using an inexistent PNR

Meta:
@flow air
@process view
@user anonymous
@traveler adult
@project view_all_pnrs
@dyna_stubs

Narrative: In order to see the OOPS message
As an anonymous user
I attempt to retrieve an Itinerary using an inexistent PNR on the View/Share Itinerary page

Scenario: Attempt to retrieve an itinerary using an inexistent PNR

Given I am on the view/share itinerary landing page
When I attempt to retrieve an itinerary using an inexistent PNR from the View/Share Itinerary page
Then I view the OOPS message for the invalid PNR on the View/Share Itinerary page
