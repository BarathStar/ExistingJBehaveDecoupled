This flow verifies that any user is allowed to search hotel for booking.

Meta:
@flow hotel
@user adult
@traveler anonymous
@process booking
@dyna_stubs

Narrative:
In order to verify the anonymous user is able to book 2 Room for 2 adults and 2 children in Hotel booking
As a user
I want to verify that I get the Select Hotel page without errors.

Scenario: Validate anonymous user is able to book 2 Rooms for 2 dults and 2 childs 

Given I am a customer looking for a hotel
And I am on the hotel search page
When the arrival city is DAL
And I want 2 rooms for 2 adults and 2 children
And I want it close to the Airport in DAL
And I click search to find Hotels
Then I get the Select Hotel page without errors