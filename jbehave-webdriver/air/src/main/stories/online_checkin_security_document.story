Senior receives security document

Meta:
@flow air
@process checking-in
@traveler senior
@not_passing draft

Narrative:

In order to board an aircraft and get through security
As a senior
I want to check-in for my flight and receive a boarding pass

Scenario: Senior customer Online Checkin

Given I am a senior customer with a previously booked flight
When I checkin for my flight
Then I should be checked in
And I should receive my security document

Scenario: ODI Senior customer Online Checkin

Given ODI Checkin On
And I am a senior customer with a previously booked flight
When I checkin for my flight
Then I should be checked in
And I should receive my security document