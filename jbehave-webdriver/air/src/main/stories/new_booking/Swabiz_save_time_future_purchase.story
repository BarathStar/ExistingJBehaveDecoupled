Verify if the credit card holder information is saved when you purchase on swabiz
with the option save time on future purchases selected

Meta:
@dyna_stubs
@flow air
@not_live
@passenger adult
@process booking
@user anonymous
@storyId SWAT-1630
@project SWAT
@project_in_dev

Narrative:
As an Adult
In order to save time on futures purchase
I want the system remember my credit card information in swabiz
So that

Scenario:Verify if the credit card holder information is saved when you purchase on swabiz with the option save time on future purchases selected

Given I am flying a round-trip Southwest Southwest flight
And I have booked a round trip flight on swabiz selecting the option save time on futures purchases
And I open the Swabiz Search Booking page
And I only search a flight on Swabiz
And I select flight in Swabiz
And I click continue to the Price page
When I click continue to the Purchase page
Then I should see the credit card holder info