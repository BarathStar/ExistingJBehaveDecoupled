Enter stations using auto-complete on the Email Us page

Meta:
@project bookingWidget
@project coda
@user anonymous
@dyna_stubs
@not_live
@project_in_dev

Narrative:
In order to enter stations using auto-complete on the Email Us page
As a user
I want to see auto-complete functionality on email us page
to ensure stations returned are correct

Scenario: Ensure only one station is shown on autocomplete list when entering letters matching that station

Given I am an Southwest costumer on the Contact Us Page
When I click E-mail Us button
And I select Dal in the From field
Then I should see Dallas (Love Field), TX - DAL in From field
