Verify that the users are able to see the message "You'll be charged for this rental upon rental car return." within shopping cart when the vendor cart is NOT Fox

Meta:
@flow car
@process booking
@traveler adult
@user anonymous
@dyna_stubs

Narrative:
As a southwest. com user booking a car
I want to see the pay message information within shopping cart to any car vendor except for Fox
So that I can see the details of my entire trip

Scenario: Verify that users are able to see the message "You'll be charged for this rental upon car rental return." within shopping cart by Fox car vendor
Given I have the following car itinerary data:
          |Field|Value|
          |pickUpStation|SFO|
          |dropOffStation|SFO|
          |pickUpDate|+1|
          |dropOffDate|+2|
          |carType|MidSize|
          |carVendor|Thrifty|
          |rentalDeskLocation|TerminalShuttle|

And I am a customer on the car reservation home page
When I search and select a car and view the Price page
Then I should see a message saying that I will be charged upon car rental return