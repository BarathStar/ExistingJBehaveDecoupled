Validate that southwest.com user can access Careers link under the About Southwest section on the page footer.

Meta:
@flow air
@traveler adult
@process view
@user anonymous
@dyna_stubs

Narrative:
As a southwest.com user
I want to go to the "Careers" page under the "About Southwest" section on the page footer
So that I can learn about career opportunities on Southwest.com.

Scenario: Any visitor to southwest.com selects "Careers" under "About Southwest" link on homepage footer.
Given I am on the Home page
When I select Careers
Then I see the Southwest Airlines Careers page is open
