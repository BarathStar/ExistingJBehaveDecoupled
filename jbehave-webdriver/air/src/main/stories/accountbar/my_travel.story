User sees upcoming flights on Manage Travel drawer on Account Bar

Meta:
@project GlobalAccountBar
@process PENDING
@flow all
@user PENDING
@not_passing draft
@message This story does not belong to any suite. It was drafted in order to not break the storymanager generator.

Narrative:  As a SWACOM/SWABIZ user,
I want to see my next flight details in Upcoming Trips section
so that I can easily be aware of my upcoming flights from My Travel drawer.

Scenario: Anonymous user sees My Travel drawer

Given I am an anonymous user
When I am on the <page> page
Then I will see a drawer with the title "My Travel" on the account bar
Examples:
|     page      |
|     Home      |
| Search Flight |
|      Help     |


Scenario: Logged-in user sees My Travel drawer

Given I am logged in
When I am on the <page> page
Then I will see a drawer with the title "My Travel" on the account bar
Examples:
|     page      |
|     Home      |
| Search Flight |
|      Help     |


Scenario: Logged-in user sees next round trip flight on Upcoming Trips section
Meta:
@not_passing draft
Given I am logged in
And I have a round trip flight
When I am on the <page> page
Then I will see my next flight with Confirmation Number
And Origin and Destination cities
And Departure date and Departure time
And Arrival time
And Default Trip Name
Examples:
|     page      |
|     Home      |
| Search Flight |
|      Help     |


Scenario: Logged-in user sees next flight that is part of a Trip on Upcoming Trips section
Meta:
@not_passing draft
Given I am logged in
And I have a flight that belongs to a trip
When I am on the Search Flight page
Then I will see the Trip Name


Scenario: Logged-in user sees next open jaw flight on Upcoming Trips section
Meta:
@not_passing draft
Given I am logged in
And I have an open jaw flight
When I am on the Search Flight page
Then I will see my next flight with Confirmation Number
And Origin and Destination cities of the first leg
And Departure date and Departure time
And Arrival time of the first leg


Scenario: User sees and interacts check-in button for flight that departs within 24h
Meta:
@not_passing draft
Given I am logged in
And I have a flight that departs within 24h
When I am on the Search Flight page
Then I will see the Check In Online button
When I click on the Check In Online button
Then I am redirected to Check-in Online page
And I will see my flight information on the Check In Online page


Scenario: User sees and interacts check-in button for flight that departs more than 24h from now
Meta:
@not_passing draft
Given I am logged in
And I have a flight that departs more than 24h from now
When I am on the Search Flight page
Then I will not see the Check In Online button


Scenario: Logged in user with no flights see Upcoming Trips section

Given I am logged in
And I don't have any flights
When I am on the Search Flight page
Then I will see a message that says "There are no upcoming trips at this moment. Book a flight now!"


Scenario: User clicks on Origin and Destination cities link
Meta:
@not_passing draft
Given I am logged in
And I have a flight
And I am on the Search Flight page
When I click on Origin and Destination cities link
Then I am redirected to Trip Details page for that flight


Scenario: User views and clicks on View All link
Meta:
@not_passing draft
Given I am logged in
And I have a flight
When I am on the Search Flight page
Then I will see the View All link
When I click on View All link
Then I am redirected to Upcoming Trips page


Scenario: SWABIZ CTM Proxy does not see the My Travel Drawer
Meta:
@not_passing draft
Given I am logged in as a SWABIZ CTM
And I am booking in behalf of a traveler
Then I will not see a drawer with the title "My Travel" on the account bar



