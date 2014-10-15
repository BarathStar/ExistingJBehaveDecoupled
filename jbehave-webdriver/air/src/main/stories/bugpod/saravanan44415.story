Book a oneway business select flight as an customer with points

Meta:
@bugpodCoreRegression
@flow air
@process booking
@user anonymous
@traveler adult
@tcid adult

Narrative:
As an customer
I want to purchase oneway business select flight with points

GivenStories:
A_initialPage/HomePage.story

Scenario: Book a oneway business select flight with points


Given I login as customer
And I want to book a oneway flight
And The departure city is ATL
And The arrival city is LAX
And The outbound trip type is nonstop
And Book the ticket for 1 passenger with passenger type adult
And I search the flight
And The outbound fare type is businessselect
And select fare in point
When I select the flight
Then I view my price details
