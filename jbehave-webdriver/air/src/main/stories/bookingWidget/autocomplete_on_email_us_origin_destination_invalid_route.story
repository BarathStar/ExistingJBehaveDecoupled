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

Scenario: Ensure invalid route message is shown on autocomplete list when entering From/To with no valid route

Given I am an Southwest costumer on the Contact Us Page
When I click E-mail Us button
And I select Sea in the From field
And I attempt to enter pdx in the To field
Then I should see the To field is empty
