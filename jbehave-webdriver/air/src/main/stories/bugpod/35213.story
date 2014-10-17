Anonymous user Round Trip air booking for Adult with Anytime Fare with OB Direct and IB one plane change and OB Checkin Availabe Flight.

Meta:
@bugpodCoreRegression
@flow air
@process booking
@user anonymous
@traveler adult
@storyId 35213

Narrative:
As an Anonymous user
I want to purchase open jaw air ticket for adult pax 

GivenStories:
A_initialPage/HomePage.story


Scenario: Book an open-jaw air ticket for adult pax .


Given I want to book a roundtrip flight
And The departure city is MSY
And The arrival city is TPA
And The outbound trip type is planechange
And The inbound trip type is direct
And Book the ticket for 1 passenger with passenger type adult
And I search the flight
When I click on the Add Another Flight hyperlink
Given select the return city SJU
When I select the flight
Then I view my price details
When I purchase the ticket
Then I view my complete itinerary on the confirmation page
When I retrieve my itinerary details
Then I view my itinerary details

