Rename a trip on the View Reservation page

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@not_passing broken
@project tmAir

Narrative:
In order to keep the original name provided to my trip
As a User
I want to retrieve my reservation and indicate an empty name as the new trip name

Scenario: Customer tries to rename a trip by providing an empty name
Given I am flying a round-trip Southwest Southwest flight
And I have booked an Air product on a trip named My Trip
When I retrieve my itinerary
Then I see the trip name My Trip
When I complete the renaming process by deleting the existing name and without entering any character
Then I see the trip name My Trip