Attempt to purchase a round-trip anytime air ticket for an adult with illegal characters in middle name

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@project_AccordionPage

Narrative:
In order to book a flight with a Khoisan name
As an adult
I fill out my middle name on the purchase page and receive an oops message

Scenario: Purchase page should should show the oops image in the error message when I type illegal characters in my middle name.
Given I am flying a round-trip Southwest Southwest flight
When I book a flight and fill in an invalid middle name
Then I should see a message about invalid characters in passenger's middle name
