Verify that the oops message appears when attempting to fill in the Click 'N Save form with an email containing not allowed special characters

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
@not_passing new_global_nav

Narrative:
As an adult
In order to verify that the oops message appears when using a email containing invalid special characters
So that I subscribe to Click 'N Save updates

Scenario: Verify oops message for invalid characters on email

Given I am on the Home page
And I go to the Click 'N Save Email Updates page
When I complete the Click 'N Save form with an invalid email and submit
Then I see an oops with the message:
 The email address entered is in an invalid format or contains characters our system does not currently support. Please provide a different e-mail address that contains only letters, numbers, periods (.), hyphens (-), and/or underscores(_) and is in the format: myname@youremailprovider.com (SW540277)