Verify an oops message appears when attempting fill the username with invalid special characters in Enroll Account Page as an user

Meta:
@flow rr
@user anonymous
@process other
@passenger adult
@dyna_stubs
@project SWAT
@story_id SWAT-2074
@project_in_dev

Narrative:
As an adult
In order to verify an oops message appears using an invalid special characters
So that I attempt to create an account

Scenario: Verify oops message for invalid characters on username

Given I am an anonymous user
And I am a Southwest User at "Create an Account" page
And I have completed all the mandatory fields to create an account
And I change my username to: Jenna♠a
When I attempt to confirm the account creation
Then I see an oops with the message: Usernames must be between 4 and 20 characters and cannot contain special characters. (SW540021)
And I see an oops with the message: You must read and accept the rules and regulations in order to complete the Rapid Rewards enrollment process. (SW540070)