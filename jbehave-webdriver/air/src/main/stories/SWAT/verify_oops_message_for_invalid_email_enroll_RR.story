Verify that the oops message appears when attempting to complete the RR enroll form with an email that contains not allowed special characters

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
So that I enroll to RR

Scenario: Verify oops message for invalid characters on email

Given I am on the Create An Account Page
When I enroll for a Rapid Reward account using an email address with not allowed special character
Then I see an oops with the message:
 The email address entered is in an invalid format or contains characters our system does not currently support. Please provide a different e-mail address that contains only letters, numbers, periods (.), hyphens (-), and/or underscores(_) and is in the format: myname@youremailprovider.com (SW540063)