Verify Check In for Altea reservation from check-in page

Meta:
@project coda
@flow air
@process checkin
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@project_in_dev
@storyid DCAIR-7701

Narrative:
In order to checkin international flight
As an adult
I want to check-in Altea reservation from check-in page

Scenario: Redirect to Altea check-in page for upcoming Altea flight

Given I am flying a round-trip Southwest flight with Altea today
And I have a flight booked
When I retrieve Altea travel documents
Then I am redirected to the Altea Check In page
