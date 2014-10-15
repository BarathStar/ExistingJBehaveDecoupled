Verifying Stored Form Of Payment Is Selected As Default

Meta:
@flow air
@process booking
@user rr_member
@traveler adult
@dyna_stubs
@not_live

Narrative:
In order to do a flight booking
As a Rapid Rewards Member
I want the stored form of payment be selected as default on Purchase page

Scenario: verify stored form of payment as default

Given I am a Rapid Rewards Member
And I login from Login page
And I have stored 1 credit cards
And I am flying a round-trip Southwest Southwest flight
When I select a flight for purchase
Then I see the stored form of payment selected