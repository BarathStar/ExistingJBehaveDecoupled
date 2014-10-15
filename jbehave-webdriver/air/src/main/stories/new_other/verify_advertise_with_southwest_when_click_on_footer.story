Verify that southwest.com user selecting Advertise with Southwest link under the  Southwest section on the page footer will go to the correct URL

Meta:
@flow air
@traveler adult
@process view
@user anonymous
@dyna_stubs


Narrative:
As a southwest.com user
I want to be able to access the Advertise with Southwest page.
So that I can look at and learn about the benefits and possibilities.

Scenario: Any visitor to southwest.com selects Advertise with Southwest link on the page footer to access Audience Targeting with Southwest page

Given I am a Southwest user at the homepage
When I select Advertise with Southwest link under the About Southwest section on the page footer
Then I see the Audience Targeting with Southwest page