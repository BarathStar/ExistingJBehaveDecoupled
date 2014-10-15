Book OW NS PNR with Promocert in my RR account

Meta:
@bugpod
@flow air
@promo_cert
@process booking
@user rr_member
@traveler adult
@storyId MH-1437

Narrative:
As a user
I want to purchase an air ticket for an RR adult with CC and PromoCode

GivenStories:
A_loginStories/PromoCert.story,
A_loginStories/AccountInfo.story

Scenario: Book a CC and promocert one-way flight

Given I am at the My Account Page
And I want to book a flight that is eligible for check in
When I select a one-way flight with a promo cert
And I click continue to the Purchase page
And I continue to the Confirmation Page
Then I see the confirmation for my PromoCert purchase
And I verify that check-in is available
