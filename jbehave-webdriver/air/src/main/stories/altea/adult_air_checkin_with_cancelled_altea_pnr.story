Check-in an Altea Air reservation that is already cancelled

Meta:
@project coda
@flow air
@process checkin
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@project_in_dev
@storyid DCAIR-7900

Narrative:
User try to check-in a cancelled Altea pnr
Display appropriate error message

Scenario: Display appropriate error message when anonymous user check-in a cancelled altea pnr

Given I am flying a round-trip Southwest flight with Altea today
And I have cancelled the booked trip
When I retrieve my reservation to checkin
Then I view an Oops message which indicates that my altea reservation may have been cancelled
