Book a revenue flight with a luv voucher

Meta:
@bugpodCoreRegression
@flow air
@process booking
@traveler adult

GivenStories:
A_initialPage/HomePage.story

Scenario: Book a revenue flight with luv voucher

Given I search for a one-way flight with Anytime fare
And I select flights and continue to Price page
And I click continue to the Purchase page
And I enter a luv voucher
When I fill out the purchase page
Then I view my complete itinerary on the confirmation page
When I retrieve my itinerary
Then I view my itinerary