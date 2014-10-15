Static booking widget scenario five with two dropdown fields and web referral

Meta:
@flow air
@process booking
@traveler adult
@project_in_dev
@project bookingWidget
@dyna_stubs

Narrative:   As a traveller, I want to use the dropdowns so that I can search the available routes.

Scenario: When I am on air_ctd_08 (StaticWidgetDefaultDepartureCustomArrival), both search fields are dropdowns and I get redirected to the Air Tran website after seeing an info modal
Given I am on the default departure and custom arrival page
When I select DAL in the custom departure dropdown
And I select HOU in the To field in the static booking widget
Then I enter valid dates and click on the search button
