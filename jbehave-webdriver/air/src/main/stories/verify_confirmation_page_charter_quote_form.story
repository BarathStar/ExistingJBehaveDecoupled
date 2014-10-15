Verify that Southwest.com user still sending the information once the interface of Charter Service page is updated.

Meta:

@flow air
@process booking
@user anonymous
@dyna_stubs
@not_live

Narrative:
As a southwest.com user,
I want to be able to submit my Charter Service form request
So that I can see the updated look-and-feel page and receive a confirmation message.

Scenario: Validate that Southwest.com user sees the updated look-and-feel page and receive a confirmation message once
he completed the Charter Services form accessing by footer.

Given I am on the Charter Services page
And I fill in the information requested by the form
When I submit the Charter Quote form
Then I should see a message saying that the Charter Service Request Form was successfully sent