Change anytime round-trip adult flight itinerary and verify page expired warning

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to avoid a double booking
As an adult
I want to change my flight and make sure pressing the browser back
button does not charge my card again

Scenario: View an opps message when the back button is pressed from the air purchase Confirmation page
Given I am flying a round-trip Southwest Southwest flight
When I have a flight booked
And I change the flight to a later date with no E-mail address input in the first try
Then I should see the itinerary change confirmation page
When I click the back button
Then I should see the document expired warning page
When I click the refresh button
Then I should see the Already Purchased Trip page
