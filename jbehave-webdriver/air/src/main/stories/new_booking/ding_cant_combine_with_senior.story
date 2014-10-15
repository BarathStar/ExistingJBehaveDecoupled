Attempt to Purchase ding & senior fares

Meta:
@flow air
@process booking
@user anonymous
@traveler senior
@dyna_stubs

Narrative:
As a Senior
I want to purchase a flight with a combination of ding and senior fares
But the system does not support this and will error

Scenario: Can't combine ding and senior fares
Given I want to fly a round-trip flight
And I am a senior
And I want to use an available Ding fare for my outbound flight
And I want to use an available senior fare for my inbound flight
When I choose a flight that matches my criteria
Then I should get an oops message that these fares are not combinable
