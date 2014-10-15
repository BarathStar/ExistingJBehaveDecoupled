Verify one way itinerary displayed after retrieve

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@user
@traveler adult
@storyId DCAIR-4861
@dyna_stubs
@project_in_dev

Narrative:
As a customer
I want to retrieve my itinerary so that I can verify me flight information
In order to verify one way itinerary displayed after retrieval

Scenario: Viewing a FL One Way Trip Itinerary
Given I am flying a one-way AirTran flight
When I have a flight booked
And I retrieve my itinerary
Then I should see the correct one-way view itinerary page
And I see the AirTran-only specific travel guidelines