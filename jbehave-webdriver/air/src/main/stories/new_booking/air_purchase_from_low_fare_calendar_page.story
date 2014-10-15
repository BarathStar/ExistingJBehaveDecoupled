Book a flight from the low fare calendar page

Meta:
@flow air
@process booking
@user anonymous
@global_nav_regression
@traveler adult
@not_passing draft

Narrative:
In order to book a flight with a low fare
As a customer
I want to search and book a flight from the low fare calendar page

Scenario: Book a flight form the low fare calendar page
Given I am flying a round-trip Southwest Southwest flight
When I search and book a flight from the low fare calendar page
Then I receive an air confirmation number
