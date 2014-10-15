View the generated PNR for a rapid rewards member booked with oneway anytime nonstop flight itinerary

Meta:
@bugpodCoreRegression
@flow air
@process view
@user anonymous
@traveler adult
@storyId MH-1060

GivenStories:
A_initialPage/HomePage.story,
A_loginStories/RRMember.story

Scenario: view an air reservation for One Way as a RR

Given I search for a one-way flight with Anytime fare
And I select and book a flight
When I retrieve my itinerary
Then I view my itinerary