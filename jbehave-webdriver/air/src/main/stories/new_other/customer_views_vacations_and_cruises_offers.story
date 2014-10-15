Check the Vacations page, so that I see the special offers and packages available.

Meta:
@flow other
@user anonymous
@global_nav_regression
@dyna_stubs

Narrative:
In order to see the special offers and packages available
As an anonymous user
I want to get to the Vacation Packages page

Scenario: Customer gets to the Vacation Packages page

Given I am a Southwest user at the homepage
When I am on the Vacations page
Then I see the special offers and packages available