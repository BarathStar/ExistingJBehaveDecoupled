User logs in using Account Bar

Meta:
@project GlobalAccountBar
@process PENDING
@flow all
@user anonymous


Narrative: As a swacom user,
I want to see the login area of the website
so that I can identify myself successfully
and navigate through all features available for registered users.


Scenario: User logs in using correct username and password on the account bar

Given I am on the <page> page
And I am an anonymous user
When I insert the correct username and password on the account bar
Then I will see my Preferred Name on the account bar
And I will not see an option to log in using the account bar
Examples:
|     page      |
|     Home      |
| Search Flight |
|      Help     |

Scenario: User logs in using correct account number and password on the account bar

Given I am on the Search Flight page
And I am an anonymous user
When I insert the correct account number and password on the account bar
Then I will see my Preferred Name on the account bar
And I will not see an option to log in using the account bar


Scenario: User enters incorrect details

Given I am on the Search Flight page
And I am an anonymous user
When I insert a wrong combination of username and password on the account bar
Then I see a login error message that says "The Username/Account Number and/or Password are incorrect."
When I insert a wrong combination of account number and password on the account bar
Then I see a login error message that says "The Username/Account Number and/or Password are incorrect."


Scenario: User enters incomplete details

Given I am on the Search Flight page
And I am an anonymous user
When I insert empty username and a password on the account bar
Then I see a login error message that says "Please enter your Username or Account Number."
When I insert an username and empty password on the account bar
Then I see a login error message that says "Please enter your Password."
When I click on login without entering any credentials on account bar
Then I see a login error message that says "Please enter your Username or Account Number."
And I see a login error message that says "Please enter your Password."


Scenario: User goes to Create Account page

Given I am on the Search Flight page
And I am an anonymous user
When I click on the "Enroll Now!" link on the login section on the account bar
Then I am redirected to Create an Account page


Scenario: User goes to Recover your Username or Password page

Given I am on the Search Flight page
And I am an anonymous user
When I click on the "Forgot Username or Password?" link on the login section on the account bar
Then I am redirected to Recover your Username or Password page


!--  TODO currently the remember me functionality is broken and another team is going to fix it.
!-- Scenario: User opts to remember login
!--
!-- Given I am on the Search Flight page
!-- And I am an anonymous user
!-- When I check the option "Remember Me"
!-- And I insert the correct username and password on the account bar
!-- Then I will see my Preferred Name on the account bar
!-- When I come back to the website
!-- Then I will see my Preferred Name on the account bar


Scenario: User sees "Need help logging in?" modal

Given I am on the Search Flight page
And I am an anonymous user
When I click on the "? icon" link on the login section on the account bar
Then I will see the "Need help logging in?" modal
When I click on the "Lookup Rapid Rewards Account #" link in the "Need help logging in?" modal
Then I am redirected to Retrieve Rapid Rewards/Account Number page


Scenario: User tries to login with invalid characters

Given I am on the Search Flight page
And I am an anonymous user
When I enter a space into the username field
Then I see a login error message that says "The Username that you entered is in an invalid format. Please try again."
When I enter an invalid character into the password field
Then I see a login error message that says "The Password that you entered is in an invalid format. Note: Passwords are case sensitive and cannot contain spaces or special characters."


!--  TODO we can't currently run this scenario because it will block the account of our test user
!-- Scenario: User tries to login with incorrect credentials too many times
!--
!-- Given I am on the Search Flight page
!-- And I am an anonymous user
!-- When I enter the incorrect username and password for 5 times in a row
!-- Then I see a login error message that says "Maximum number of tries reached. Please try again later"
