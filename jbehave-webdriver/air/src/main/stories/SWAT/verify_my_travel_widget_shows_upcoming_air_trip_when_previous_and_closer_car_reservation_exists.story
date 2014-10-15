Verify that My Travel widget on account bar shows the upcoming air trip when there is a car reservation with date close to the air trip

Meta:
@flow rr
@process view
@user rr_member
@traveler adult
@project SWAT
@dyna_stubs
@not_live
@project_in_dev


Narrative:
As an adult
I want to book a flight with my RR account
So that I get into the purchase page and access to my RR account

Scenario:Verify upcoming trip is showed in My Travel widget

Given I am a Rapid Rewards Member passenger
And I am:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have selected the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DAL|
    |arrivalStation|MDW|
    |departureDate|+15|
    |returnDate|+16|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|WannaGetAway|
    |inboundRouting|Nonstop|

And I have the following car itinerary data:
    |Field|Value|
    |pickUpStation|DAL|
    |dropOffStation|DAL|
    |pickUpDate|+13|
    |dropOffDate|+14|
    |carType|MidSize|
    |carVendor|Hertz|
    |promoCode|1234|

And I have booked an Air product on a trip named UpcomingTrip
And I have a car Reservation in the Upcoming Trip in my account
And I am logged in as a Rapid Rewards Member
When I click in My Travel link in the account bar
Then I see the upcoming flight being showed in My Travel in the account bar