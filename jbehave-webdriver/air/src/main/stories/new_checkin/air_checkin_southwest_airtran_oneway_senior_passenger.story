Verify error message shown when I check-in a reservation with code share itinerary as a Non-age verified Senior

Meta:
@flow air
@process checkin
@passenger senior
@user anonymous
@dyna_stubs
@not_live

Narrative:
As a senior
I want to check-in for codeshare PNR with non-age verified and I get an error message
So that

Scenario: Verify error message shown when I check-in a reservation with code share itinerary

Given I am flying a one-way CodeShare flight with a 1 stop segment
And I have the following itinerary data:
    |Field|Value|
    |departingFlight_fareClass|Senior|
When I book a flight eligible for checkin 1 senior
And I retrieve travel documents
Then I see an oops with the message: Your Itinerary is ineligible for check in online.