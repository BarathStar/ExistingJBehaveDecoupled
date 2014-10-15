Verify if attribute HttpOnly is settled in DiscCookie at Search Flight page as Anonymous User

Meta:
@flow other
@process view
@user adult
@passenger adult
@dyna_stubs

Narrative:
As an Adult
I want to see DiscCookie is present but I cannot access with JavaScript at Search Flight page
So that

Scenario: I on Search Flight page and I want to see DiscCookie is present but I cannot access with Javascript

Given I am on the Search Flight page
When I verify cookie named DiscCookie it is present in browser
Then I shouldn't get cookie with javascript named DiscCookie