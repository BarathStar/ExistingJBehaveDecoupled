Cancel a car reservation for an adult

Meta:
@flow car
@process cancel
@user anonymous
@traveler adult
@not_passing draft

Narrative:
In order to cancel my car reservation
As an adult
I want to receive a cancellation confirmation

Scenario: Cancel a car reservation
Given I am a customer on the car reservation home page
When I reserve a car
And I click cancel on the car confirmation page
Then I should see the single car product cancellation modal
When I accept the car cancellation
Then I verify that my car reservation has been cancelled
