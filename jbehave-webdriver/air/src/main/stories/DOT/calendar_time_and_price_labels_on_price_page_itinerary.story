Calendar format, time format, and price labels on price page

Meta:
@project dot
@flow air
@process booking
@user anonymous
@traveler adult

Narrative:
As a customer
I want to select a round trip flight
In order to verify the redesigned calendar, time, and price labels on the price page itinerary

Scenario: Viewing redesigned calendar, time, and price labels on price page
Given I am flying a round-trip Southwest Southwest flight
And I am on the select flights page
When I select flights and continue
Then I should see that the price page itinerary has the day of the week and date calendar icon
And I should see that the price page itinerary has the two digit hour time format
And I should see that the price page air section header has the total price label
