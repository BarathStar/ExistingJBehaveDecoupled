Retrieve existing Altea flight itinerary

Meta:
@project coda
@flow air
@process view
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@project_in_dev
@storyid PODIV-1387

Narrative: I am a southwest.com user with a confirmed International booking
AND I am in the VIEW Reservation flow. When I enter the FIRST NAME, LAST NAME
and CONFIRMATION NUMBER AND click on CONTINUE then I will be redirected to WDS BKGD

Scenario: Redirect View Reservation for upcoming Altea flight

Given I am flying a round-trip Southwest flight with Altea
And I have a flight booked
When I retrieve my Air itinerary
Then I am redirected to the Altea View Reservation page
