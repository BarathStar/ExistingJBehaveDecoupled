Verify if attribute HttpOnly is settled in DiscCookie at Create Account page as Anonymous user

Meta:
@flow other
@process view
@user adult
@passenger adult
@dyna_stubs

Narrative:
As an Adult
I want to see DiscCookie is present but I cannot access with JavaScript at Create Account page
So that

Scenario: I on Create Account page and I want to see DiscCookie is present but I cannot access with Javascript

Given I am a Southwest User at "Create an Account" page
When I verify cookie named DiscCookie it is present in browser
Then I shouldn't get cookie with javascript named DiscCookie