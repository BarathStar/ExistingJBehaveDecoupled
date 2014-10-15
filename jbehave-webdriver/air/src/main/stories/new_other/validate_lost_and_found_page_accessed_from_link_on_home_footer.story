Validate that "Lost and Found" page is displayed correctly when it is accessed from "Lost and Found" link on the footer

Meta:
@flow air
@traveller adult
@process view
@user anonymous
@dyna_stubs

Narrative:
As a user
I want to verify that I see "Lost and Found" page when I click on "Lost and Found" link on the footer
So that I can access that page easily

Scenario: Access to "Lost and Found" page from link on the footer as anonymous in Southwest.com

Given I am on the Homepage
When I click on Lost and Found Link on the page footer
Then I see Lost and Found page