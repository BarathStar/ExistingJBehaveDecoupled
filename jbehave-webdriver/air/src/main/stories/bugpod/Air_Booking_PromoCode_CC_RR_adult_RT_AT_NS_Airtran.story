Booking Airtran Adult Round-Trip Anytime flight for an RR member using Credit Card and Promocode.

Meta:
@bugpod
@flow air
@process booking
@user rr_member
@traveler adult
@storyID MH-1391

Narrative:
As a user
I want to purchase a roundtrip air ticket as an RR member adult with CC and PromoCode

GivenStories:
A_initialPage/HomePage.story,
A_loginStories/RRMember.story

Scenario: Book a CC, Anytime, Nonstop, PromoCode Airtran round-trip flight

Given I search for an Airtran round-trip flight with Anytime fare and promocode
When I select flights and continue
And I continue to the purchase page and complete the booking
And I retrieve my itinerary
Then I view my itinerary