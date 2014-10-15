Cancel a OW PNR with anytime fare on a non stop flight with a RR account.

Meta:
@bugpod
@flow air
@process cancel
@user rr_member
@traveler adult
@storyId MH-1446

GivenStories:
bugpod/Air_Booking_Promocert_RR_adult_OW_NS_Checkin_eligible.story

Scenario: A RR member cancel the flight

Given I am on the Home page
When I cancel the flight
Then I see the Cancellation Confirmation Page