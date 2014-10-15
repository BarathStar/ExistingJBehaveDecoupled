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

Scenario: Ensure correct stations are shown on autocomplete list when entering From/To for a valid route

Given I am an Southwest costumer on the Contact Us Page
When I click E-mail Us button
And I select DAL in the From field dropdown
Then I should see Dallas (Love Field), TX - DAL in From field
When I select MDW in the To field dropdown
Then I should see Chicago (Midway), IL - MDW in To field
