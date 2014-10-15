Reserve and Cancel a Hotel Room

Meta:
@flow hotel
@process cancel
@user anonymous
@message Generic Oops message with no check for Oops on Hotel Search Form

Narrative:
In order to receive a cancellation confirmation
As an adult
I want to cancel my reservation and receive a cancellation confirmation number


Scenario: Customer Reserve, View, and Cancel Hotel
Given I have the following hotel itinerary data:
        |Field|Value|
        |destination|DAL|
        |checkInDate|+2|
        |checkOutDate|+4|

And I am a customer looking for a hotel
When I reserve a hotel room
And I receive a hotel confirmation number
And I cancel a hotel reservation
Then I receive confirmation that my hotel reservation is canceled
