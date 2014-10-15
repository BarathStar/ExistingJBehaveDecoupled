Rename a trip by providing valid/invalid characters and exceeding the maximum number allowed

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@project tmAir
@dyna_stubs
@not_passing broken

Narrative:
In order to rename my trip
As an Anonymous User
I want to retrieve my reservation and be able to indicate a new trip name

Scenario: Customer tries to rename a trip with valid and invalid characters and exceeding the maximum characters allowed

Given I am flying a round-trip Southwest Southwest flight
And I have booked an Air product on a trip named My Trip
When I retrieve my itinerary
And I complete the renaming process by entering a new name with valid and invalid characters
Then I see the valid characters and I should not see the invalid characters entered as part of the new name