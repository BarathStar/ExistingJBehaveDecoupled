Contact Southwest Airlines through email, so that I can send a communication.

Meta:
@flow other
@process email
@user anonymous
@dyna_stubs

Narrative:
As an anonymous user
I want to contact Southwest Airlines through email
In order to send a communication

Scenario: User sends an email to Southwest Airlines and the Thank you modal window appears
Given I am on the Home page
When I click on Contact Us link
And I click E-mail Us button
And I fill in the required fields
And I click on the Send E-mail button
Then I see the Thank you! modal
When I click on the Close link in the Thank you! modal
Then I should not see the Thank you! modal
And I return to the main Contact Us page