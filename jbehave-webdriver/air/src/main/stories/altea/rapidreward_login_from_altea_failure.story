Invalid Login from Altea

Meta:
@flow air
@process booking
@user rr_member
@traveler adult
@project coda
@dyna_stubs
@not_live
@project_in_dev
@storyid PODIV-1306

Narrative:
In order to receive a failure response
As an invalid member on the International site
I want to fail login from the International site Account Bar

Scenario:Login from the international site account bar

Given I am an invalid user on the International site
When I send an international login request
Then I should fail login and receive a failure response
