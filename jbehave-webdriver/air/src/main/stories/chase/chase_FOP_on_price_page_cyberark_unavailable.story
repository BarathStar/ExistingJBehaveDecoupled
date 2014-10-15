Apply for Chase Credit on price page while CyberArk is down

Meta:
@project 5.22ffClassicRetirement
@project_in_dev
@flow air
@process booking
@traveler adult
@user Anonymous
@not_live

Narrative:

As a customer
I want to apply for Chase credit card on pricing page
So that I can use it as FOP for the flight booking but CyberArk is unavailable for encryption

Scenario: Creating a flight booking with Chase as FOP on pricing page while CyberArk is unavialable for encryption

Given I am flying a round-trip Southwest Southwest flight
And I am on the select flights page
And I select the flight and view the Price page
When I make a chase purchase while CyberArk is unavailable
Then I receive an OOPS message to use an alternate method of payment on pricing page
