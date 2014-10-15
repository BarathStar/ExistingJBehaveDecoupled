Verify if attribute HttpOnly is settled in DiscCookie at Rapid Reward account page as Rapid Reward Member user

Meta:
@flow other
@process view
@user adult
@passenger rr_member
@dyna_stubs
@not_live

Narrative:
As an Adult
I want to see DiscCookie is present but I cannot access with JavaScript at Rapid Reward account page
So that

Scenario: I on Rapid Reward account page and I want to see DiscCookie is present but I cannot access with Javascript

Given I am a Rapid Rewards Member
And I am logged in as a Rapid Rewards Member
When I verify cookie named DiscCookie it is present in browser
Then I shouldn't get cookie with javascript named DiscCookie