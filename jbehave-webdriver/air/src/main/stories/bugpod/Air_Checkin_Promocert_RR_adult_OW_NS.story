Checkin a Rapid Reward member for a OW nonstop flight with anytime fare

Meta:
@bugpod
@flow air
@promo_cert
@process checkin
@user rr_member
@traveler adult
@storyId MH-1454

Narrative:
As a user
I want to purchase a one changes plane air ticket for an RR adult with CC

GivenStories:
bugpod/Air_Booking_Promocert_RR_adult_OW_NS_Checkin_eligible.story,
A_initialPage/HomePage.story

Scenario: Adult RR passenger checkin for a flight

Given I am on the check-in page
When I checkin with MBP selected
Then I view my boarding pass
