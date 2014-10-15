Purchase an air ticket as a SwaBiz User

Meta:
@flow air
@process booking
@user sb_user
@traveler adult
@not_passing draft

Narrative:
In order to fly on a date that I want
As a SwaBiz User
I want to book a flight and
receive a confirmation number


Scenario: SWABiz User air purchase
Given I am flying a round-trip Southwest Southwest flight
And I login as a Traveler
When I search for a flight
And I select and purchase a flight
Then I should see the SWABiz confirmation page
