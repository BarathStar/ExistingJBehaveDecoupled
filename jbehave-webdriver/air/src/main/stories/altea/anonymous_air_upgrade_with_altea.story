Upgrade existing Altea flight itinerary

Meta:
@project coda
@flow air
@process upgrade
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@project_in_dev
@storyid PODIV-1390

Narrative: I am a southwest.com user with a confirmed International booking AND I am in the UPGRADE Reservation flow.
When I enter the FIRST NAME, LAST NAME and CONFIRMATION NUMBER AND click on CONTINUE
Then I will be redirected to WDS SFUP

Scenario: Redirect Upgrade Reservation for upcoming Altea flight

Given I am flying a round-trip Southwest flight with Altea
And I have a flight booked
When I am on the Homepage
And I click on Business Select option on the page footer
And I fill the Business Select form and submit
Then I am redirected to the Altea Upgrade Reservation page
