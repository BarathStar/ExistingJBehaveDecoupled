My Account Entry from Altea

Meta:
@flow air
@process booking
@user rr_member
@traveler adult
@project coda
@dyna_stubs
@project_in_dev
@storyid PODIV-1311

Narrative:
In order to view the My Account page
As a Rapid Rewards member on the International site
I want to login from the International site Account Bar and click on the My Account link

Scenario: Enter My Account page from the international site account bar

Given I am an RR user on the International site
When I send an international login request
Then I should be logged in and receive the proper response
And I should enter the My Account page successfully
