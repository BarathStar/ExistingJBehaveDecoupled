View dummy Itinerary


Meta:
@project cr1
@flow air
@airTranOnly
@process booking
@traveler adult
@user anonymous
@storyId DCAIR-4890,DCAIR-5432 ZR-895
@project_in_dev

Narrative:
As a customer
I want to enter dummy pnr and dummy first/last name on view itinerary page
So that I may view generic View/Share Itinerary oops message


Scenario: View Itinerary

Given I am on the Itinerary Page
When I enter <dummy_pnr> <dummy_fname> <dummy_lname>
Then I view the oops messages <msgs>

Examples:
|dummy_pnr|dummy_fname|dummy_lname|msgs|
|dum123|dummyfname|dummylname|The confirmation number is entered correctly.,The passenger name on the reservation is entered correctly.,The reservation was purchased online via southwest.com|

