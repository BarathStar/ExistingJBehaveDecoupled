Verify Account Bar is visible on static pages

Meta:
@suite SWAT
@process booking
@flow all
@user anonymous
@dyna_stubs
@storyId SWAT-2189,PODIV-1335

Narrative:  AccountBar should be visible on static pages
As an Anonymous user
I want to see the AccountBar on wherever I am on the site
So that I can login from any screen

Scenario: AccountBar visible on Static pages
Given I am on the Homepage
When I select International Travel
Then I see the Account Bar
