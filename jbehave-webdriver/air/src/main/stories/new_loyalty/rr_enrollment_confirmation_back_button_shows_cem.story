Loyalty - This flow verifies that when the user chooses to edit the address information that has been successfully cleansed on the Review Page using the back button browser, application works properly and Stack trace is not being displayed.

Meta:
@dyna_stubs
@flow rr
@process other
@user anonymous
@passenger adult

Narrative:
As anonymous user who just enrolled
I should go back to a Custom Error Message page using browser back button
I can then click a link to view the Confirmation Page and another to view the Account Info Page

Scenario: Anonymous user registers and tries to go to previous pages using back browser button.

Given I go to the Enrollment Confirmation page
When I click the back button
Then I should see the Already Enrolled Account page