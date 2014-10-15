Nonrev Air Cancel

Meta:
@project nonrev
@flow air
@process checkin
@user anonymous
@traveler adult
@dyna_stubs
@not_passing draft
@PODIV-863
@FF-ODI
@not_live

Narrative:
In order to receive my security document
As an adult
I want to checkin for a flight

Scenario: Nonrev Cancel

Given I am flying a round-trip Southwest Southwest flight
When I book a nonrev flight
And I proceed to nonrev retrieve/cancel air itinerary page
When I retrieve nonrev itinerary
And I click the nonrev cancel button
Then I verify that the nonrev canceled page is displayed
