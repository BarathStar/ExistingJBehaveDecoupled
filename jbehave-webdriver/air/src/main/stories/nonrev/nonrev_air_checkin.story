Nonrev Air Checkin

Meta:
@project nonrev
@flow air
@process checkin
@user anonymous
@traveler adult
@dyna_stubs
@not_passing draft
@PODIV-849
@FF-ODI
@not_live

Narrative:
In order to receive my security document
As an adult
I want to checkin for a flight


Scenario: Nonrev Checkin

Given I am flying a round-trip Southwest Southwest flight
When I book a nonrev flight
And I proceed to nonrev checkin page
And I click checkin to create a nonrev security document
Then I view my security document