Add an air product to an existing trip so that cross sell is empty for the products already added to the trip in the Confirmation Page

Meta:
@flow air
@process booking
@user rr_member
@traveler adult
@dyna_stubs
@not_live

Narrative:
In order to add my air product to an exiting trip in my account
As a Rapid Rewards Member (with saved trips in my account)
I want to be shown the existing trips from my account, add my air product to one of them and
validate the information shown on the Confirmation Page where I am not shown the
cross sell section for products which are already in the trip

Scenario:
Rapid Rewards Member (with saved trips) adds an Air product to an
existing trip and does not view cross sell section for products already added to the trip
Given I am a Rapid Rewards Member passenger
And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |departureDate|+2|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|
    |earlyBird|true|
    |earlyBirdPurchased|true|

And I have the following car itinerary data:
    |Field|Value|
    |pickUpStation|DAL|
    |dropOffStation|DAL|
    |pickUpDate|+2|
    |dropOffDate|+2|
    |carType|MidSize|
    |carVendor|Budget|

And I have included an Air in my shopping cart
And I have included a Car in my shopping cart
And I have finished my purchase adding my product to a new trip named My Trip
When I log in from the account sidebar at the Search Flights page
And I want to book a round-trip Southwest Southwest flight
And I search and select a flight and am on the purchase page
Then I see the trip section where the option to name the trip is offered by default on the Purchase page
And I see the new trip name radio button selected on the Purchase page
And I see that the new trip name has the default format
And I should see the existing trips drop down disabled on the Purchase page
When I complete the booking process by adding the Air product to the existing trip My Trip
Then I see that the Air product just booked has the name My Trip on the Confirmation Page
And I see references to the product added to the trip on the Confirmation page
And I see the products which belong to the existing trip with their details hidden on the Confirmation page
And I see an informative text indicating that the existing air product has purchased the EB check-in on the Confirmation Page
And I should not see the Car Cross Sell Section on the Confirmation Page