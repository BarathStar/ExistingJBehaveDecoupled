Static booking widget scenario five with an autocomplete and a dropdown

Meta:
@flow air
@process booking
@traveler adult
@project_in_dev
@project bookingWidget
@dyna_stubs

Narrative:   As a traveller, I want to use an autocomplete and a dropdown so that I can search the available routes.

Scenario: When I am on air_ctd_07 (StaticWidgetCustomDepartureDefaultArrival), both search fields are dropdowns and I get redirected to the Air Tran website after seeing an info modal
Given I am on the custom departure and default arrival page
When I select HOU in the From field in the static booking widget
And I select DAL in the default departure dropdown
Then I enter valid dates and click on the search button
