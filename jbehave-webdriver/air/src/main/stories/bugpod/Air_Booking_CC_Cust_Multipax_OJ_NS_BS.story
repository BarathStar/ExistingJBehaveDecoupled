Customer air booking for a customer and passenger for an Open Jaw flight with Business Select fare

Meta:
@flow air
@process booking
@user customer
@traveler adult
@storyId MH-1399

GivenStories:
A_initialPage/HomePage.story,
A_loginStories/Customer.story

Scenario: Book a multipax open jaw flight as a Customer

Given I navigate to the book a flight page
And I search for an open-jaw flight for 2 adults with Nonstop Business Select fare
When I book the trip
Then I view my complete itinerary on the confirmation page