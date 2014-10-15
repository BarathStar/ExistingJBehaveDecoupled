Verify that the oops message appears when attempting fill the Contact Us form with an email containing not allowed special characters

Meta:
@dyna_stubs
@flow air
@user anonymous
@process other
@passenger adult
@project SWAT
@project_in_dev
@not_live
@story_id SWAT-2679

Narrative:
As an adult
In order to verify that the oops message appears when using a email containing invalid special characters
So that I contact Customer Service by filling in the Contact Us form

Scenario: Verify oops message for invalid characters on email
Given I am an Southwest costumer on the Contact Us Page
And I click E-mail Us button
And I fill in the required fields using an invalid email address
When I click on the Send E-mail button
Then I see an oops with the message:
    The email address entered is in an invalid format or contains characters our system does not currently support. Please provide a different e-mail address that contains only letters, numbers, periods (.), hyphens (-), and/or underscores(_) and is in the format: myname@youremailprovider.com (SW111020)