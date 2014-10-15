Anonymous Round Trip air booking for One Senior for a Non Stop Flight with Senior Fare

Meta:
@bugpod
@flow air
@process booking
@user anonymous
@traveler senior
@storyId MH-1393

Narrative:
As a user
I want to purchase an air round trip ticket for one anonymous senior for a non stop flight with CC and Senior fare

GivenStories:
A_initialPage/HomePage.story

Scenario: Book a revenue one-way flight

Given I search for a round-trip flight for 1 senior(s) with Senior fare
When I book a senior fare flight and try to book by entering invalid date of birth
Then I see an invalid senior date of birth message
When I enter the correct date of birth and book my flight
Then I view my complete itinerary on the confirmation page
When I retrieve my Senior itinerary
Then I view my Senior itinerary