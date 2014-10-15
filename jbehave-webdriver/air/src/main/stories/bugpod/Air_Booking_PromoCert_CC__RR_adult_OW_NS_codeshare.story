Create new one way PNR using Promo Cert & Codeshare

Meta:
@storyId MH-1411
@bugpod
@promo_cert
@flow rr
@process booking
@user rr_member
@traveler adult
@not_passing broken

Narrative:
As a customer logged into R.R account with promo-certs
I want to book a one way flight with a promo-cert with codeshare
So that I am not spending money

GivenStories:
A_loginStories/PromoCert.story,
A_loginStories/AccountInfo.story

Scenario: Book a CC round trip flight with codeshare

Given I am at the My Account Page
When I book a one way flight with a promo cert and codeshare
Then I see the confirmation for my PromoCert purchase
