Verify if attribute HttpOnly is settled in DiscCookie at Interactive Route Map page as Anonymous user

Meta:
@flow other
@process view
@user adult
@passenger adult
@dyna_stubs

Narrative:
As an Adult
I want to see DiscCookie is present but I cannot access with JavaScript at Interactive Route Map page
So that

Scenario: I on Interactive Route Map page and I want to see DiscCookie is present but I cannot access with Javascript

Given I am on the interactive route map page
When I verify cookie named DiscCookie it is present in browser
Then I shouldn't get cookie with javascript named DiscCookie