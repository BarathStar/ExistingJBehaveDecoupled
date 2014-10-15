Select "Where We Fly" link from the search flight Page

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
I want to Select the "Where We Fly" link from the search flight Page

Scenario: Viewing destinations ('See Where We Fly' link) on the Search Flight page

Given I am on the Search Flight page
When I click where we fly link on the search page
Then I see the destination route map
