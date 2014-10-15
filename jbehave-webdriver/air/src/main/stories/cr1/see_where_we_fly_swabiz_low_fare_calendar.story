Select "Where We Fly" link from swabiz low fare calendar Page

Meta:
@project cr1
@flow air
@process Information Search
@traveler adult
@storyId DCAIR-4742, DCAIR-4798, DCAIR-4902
@not_passing draft
@see_where_we_fly
@message This link no longer exists so this needs to be cleaned up.  Steps and page class need to cleaned up to. (DCAIR-7082)

Narrative:
In order to show the additional AirTran destinations
As a customer
I want to Select the "Where We Fly" link from the swabiz low fare calendar Page

Scenario: Viewing destinations ('See Where We Fly' link) on the SWABiz Shortcut(Low Fare Calendar) page

Given I have anonymously logged into a SWABiz account
And I am on the Low Fare Calendar page
When I click where we fly link on the swabiz low fare calendar
Then I see the destination route map
When I click list view tab
Then I see the list of Albany, NY - ALB destinations
When I choose to view filterINTL destinations
Then I see the list of Acapulco, MX - ACA destinations
When I choose to view filterALL destinations
Then I see the list of Acapulco, MX - ACA destinations
When I click route map tab
Then I see the destination route map
