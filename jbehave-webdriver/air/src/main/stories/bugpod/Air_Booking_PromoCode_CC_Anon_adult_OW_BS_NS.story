Purchase adult air Business Select, Anonymous Adult Anytime ticket with CC and PromoCode

Meta:
@bugpodCoreRegression
@flow air
@process booking
@user anonymous
@traveler adult
@not_passing outOfScope

Narrative:
As a user
I want to purchase an air ticket for an anonymous adult with CC and PromoCode

GivenStories:
A_initialPage/HomePage.story

Scenario: Book a CC and PromoCode one-way flight

Given I search for a one-way flight with Business Select fare with promocode
And I select and book a flight
When I retrieve my itinerary
Then I view my itinerary