Customer air booking for a customer and passenger for an Open Jaw flight with Business Select fare and selects Flight Status Messaging

Meta:
@bugpod
@flow air
@process booking
@user customer
@traveler adult
@storyId MH-1399

Narrative: In order to verify the default options are selected on the flight status notification page
As a customer
I want to search for a flight with a passenger, after I complete a booking I click on the Flight Status Messaging link

GivenStories:
bugpod/Air_Booking_CC_Cust_Multipax_OJ_NS_BS.story

Scenario: I subscribe to Flight Status Messaging from the Air purchase Confirmation Page

Given I am on the confirmation page after a successful booking
When I click Subscribe to Flight Status Messaging link from the confirmation page
Then I should see default options selected on the flight status notification page
