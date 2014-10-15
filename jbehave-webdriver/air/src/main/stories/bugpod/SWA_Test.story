
Book a Round Trip non stop chekin eligible flight for an Inhibited Adult with Anytime Fare as an Anonimus user

Meta:
@bugpodCoreRegression
@flow air
@process booking
@user anonymous
@traveler adult
@storyId MH-1540


Narrative:
As an Anonymous user
I want to purchase Round trip non stop anytime checkin eligible ticket for inhibited adult

GivenStories:
A_initialPage/HomePage.story

Scenario: Book a Round Trip non stop chekin eligible flight for an Inhibited Adult with Anytime Fare as an Anonimus user


Given I want to book a oneway flight
And The departure city is ATL
And The arrival city is LAX
And The outbound carrier type is WN
And The inbound carrier type is WN
And The outbound trip type is non stop
And The inbound trip type is non stop
And The outbound fare type is anytime
And The inbound fare type is anytime
And Book the ticket for 1 passenger with passenger type adult
And I search the flight
When I click on the Add Another Flight hyperlink
When I select the flight
Then I view my price details
When I purchase the ticket
Then I view my complete itinerary on the confirmation page
When I retrieve my itinerary details
Then I view my itinerary details
