Verify that Southwest.com user sees the new options when clicking on the "If outside the U.S." option button on Connect with us form.

Meta:
@flow other
@process Contact Us
@traveler adult
@user anonymous
@dyna_stubs
@not_live

Narrative:
As a Southwest.com user submitting a Contact Us form
I want to select "If outside the U.S." option to enter my contact information
So that I can provide my international address when living outside the United States.

Scenario: User sends an email to Southwest Airlines using the new form.

Given I am an Southwest costumer on the Contact Us Page
And I click E-mail Us button
And I select the 'If outside the U.S.' option
And I fill in the required information
When I click on the Send E-mail button
Then I see the Thank you! modal
