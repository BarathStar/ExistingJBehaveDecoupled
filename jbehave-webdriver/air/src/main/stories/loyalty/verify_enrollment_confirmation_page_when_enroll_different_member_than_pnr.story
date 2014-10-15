Verify that when a enrolling from the Enter Rapid Rewards Number Page that it does not redirect to Add Rapid Rewards Number
when user is not on PNR

Meta:
@flow rr
@process loyalty
@user anonymous
@traveler adult
@passenger adult
@dyna_stubs
@project avengers_13.11
@project_in_dev
@storyid AV-2395

Narrative:
As an anonymous user
I want to book a flight for 2 more passengers and and enroll from Add Rapid Rewards Page, and not be redirected back when account is not on PNR


Scenario: User click the enroll image on Enter Rapid Rewards Number page and enroll in an account not on PNR it redirects to enrollment confirmation page.

Given I am flying with the following passenger: John Smith
And I am flying with the following passenger: Smith Johns
And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |departureDate|+1|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

And I have finished my purchase adding my products to a new trip named My Trip
And I am on the Add Rapid Rewards Number Page
When I enter my PNR on the add Rapid Rewards number page
And I click on the wcm image for enrollment
And I submit the Enrollment form that has been properly filled for a random user
And I confirm my account creation on the Review Page
Then I view RR Sign Up Module in Confirmation Page
