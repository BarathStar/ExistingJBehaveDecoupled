Verify that southwest.com user selecting "Southwest Merchandise" link under the "Southwest Products" section on the page footer will go to the correct URL

Meta:
@flow air
@traveller adult
@process view
@user anonymous
@dyna_stubs

Narrative:
As a southwest.com user
I want to be able to access the "Southwest Merchandise" page.
So that I can look at and purchase items from Southwest Airlines.

Scenario: Any visitor to southwest.com selects "Southwest Merchandise" link on homepage footer at www.swafreedomshop.com

Given I am on the Homepage
When I select Southwest Merchandise under the Southwest Products section on the page footer
Then I see the Southwest Freedom Shop home page