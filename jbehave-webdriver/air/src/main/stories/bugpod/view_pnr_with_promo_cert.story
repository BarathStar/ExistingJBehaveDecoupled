create new round trip PNR with promo cert

Meta:
@storyId MH-704
@bugpodCoreRegression
@promo_cert
@flow rr
@process booking
@user rr_member
@traveler adult
@not_passing broken

Narrative:
As a customer logged into R.R account
I want to view my previously booked PNR's
So that I can verify my flights

GivenStories:
bugpod/create_pnr_with_promo_cert.story

Scenario: Log in as Rapid Rewards user, book round trip flight

Given I am at the View/Share Itinerary Page
When I enter my information to retrieve my reservation
Then I can see my itinerary booked with a promo cert