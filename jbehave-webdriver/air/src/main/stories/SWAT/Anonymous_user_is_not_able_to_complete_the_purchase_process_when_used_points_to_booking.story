Verify if at attempt to book a flight with points, when the user try to go from the price page to the purchase page, it is redirected to the login page

Meta:
@flow air
@process view
@user anonymous
@passenger adult
@project SWAT
@project_in_dev
@dyna_stubs
@storyId SWAT-2620

Narrative:
As an adult
In order to verify if when I attempt to book a flight as anonymous with point I am redirect to login page
So that

Scenario: Verify when I attempt to book a flight as anonymous I am redirect to login page

Given I am flying a one-way Southwest flight
And I am a customer searching for a flights with points
And I select the flight and view the Price page
And I come back to select flight
And I modify my search for new stations
And I click the back button
And I click the back button
When I continue to the Purchase page
Then I see the My Account Login page