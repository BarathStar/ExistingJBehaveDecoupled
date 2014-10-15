Example for a page object


Meta:
@flow air
@process booking
@user anonymous
@not_passing draft

Narrative: In order to show how to use the new PageObject approach based on PageFactory.initElements
As a user
I want to verify that an error message is received

Scenario: User tries to search for a flight without select a departure city in a Round Trip
Given I am an adult customer in the flight page
When I click on the RoundTrip button
And I fill las in Destination City
And I click on the search button
Then I see an Oops message No departure airport selected for the outbound flight 

Scenario: User clicks on OneWay button
Given I am an adult customer in the flight page
When I click on the OneWay button 
Then I see the Return Date field disable

Scenario: User search for a flight selecting Departure City, Destination City, Depart date and Return date
Given I am an adult customer in the flight page
When I click on the RoundTrip button 
And I fill alb in Origin City
And I fill las in Destination City
And I fill tomorrow in Depart Date
And I fill dayAfterTomorrow in Return Date
And I click on the search button
Then I see the page select-flight.html

Scenario: User clicks on the flight number link
Given I am an adult customer in the select page with an Itinerary selected
When In the outbound flights table, I click the flight number link on the row #3
Then I see a modal panel with the connecting information

Scenario: User tries to click on select button without select a departure city
Given I am an adult customer in the select page with an Itinerary selected
When I select the Outbound Flight #2
And I click on the continue button
Then I see an Oops message No Inbound Flight selected.

Scenario: User Selects a flight in the Select Page
Given I am an adult customer in the select page with an Itinerary selected
When I select the Outbound Flight #2
And I select the Inbound Flight #2
And I click on the continue button
Then I see the page price-reservations.html
And I see the Itinerary Albany, NY to Las Vegas, NV

Scenario: User confirms the Itinerary on the page Pricing and Restrictions
Given I am an adult customer in the Pricing and Restrictions page 
When I click on continue button
Then I see the page book-reservations.html

Scenario: User ties to Confirm the purchase with an incomplete form
Given I am an adult customer in the Purchase page
When In the Who's Flying form I fill *** in First Name, *** in Last Name
And I select September in Month, 10 in Day, 1985 in Year and male in Gender
And I click on the purchase button
Then I view the flight cancellation confirmation