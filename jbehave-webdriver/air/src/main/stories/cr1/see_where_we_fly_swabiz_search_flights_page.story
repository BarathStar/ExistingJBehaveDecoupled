Select "Where We Fly" link from swabiz search Page

Meta:
@project cr1
@flow air
@process Information Search
@traveler adult
@storyId DCAIR-4742, DCAIR-4798, DCAIR-4902
@see_where_we_fly
@dyna_stubs
@not_passing draft
@message This link no longer exists so this needs to be cleaned up.  Steps and page class need to cleaned up to. (DCAIR-7082)

Narrative:
In order to view additional domestic AirTran destinations
As a customer in SWABIZ
I want to open the destination route map and list from the See Where We Fly link

Scenario: View stations ('See Where We Fly' link) on the SWABiz Search Flight page


Given I have anonymously logged into a SWABiz account
When I click where we fly link on the swabiz search page
Then I see the destination route map
When I click list view tab
Then I see the list of domestic multi-carrier destinations
When I click route map tab
Then I see the destination route map
