Create new round trip PNR using Promo Cert

Meta:
@storyId MH-703
@bugpodCoreRegression
@promo_cert
@flow rr
@process booking
@user rr_member
@traveler adult
@not_passing broken

Narrative:
As a customer logged into R.R account with promo-certs
I want to book a round trip flight with a promo-cert
So that I am not spending money

GivenStories:
A_loginStories/PromoCert.story,
A_loginStories/AccountInfo.story

Scenario: Log in as Rapid Rewards user, book round trip flight

Given I am at the My Account Page
When I select a round trip flight with a promo cert
And I click continue to the Purchase page
And I continue to the Confirmation Page
Then I view my complete itinerary on the confirmation page