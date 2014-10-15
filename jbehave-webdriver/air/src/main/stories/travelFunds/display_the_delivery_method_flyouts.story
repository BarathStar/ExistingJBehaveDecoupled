User displays the Delivery Method FAQs

Meta:
@flow travel extras
@process view
@suite regression
@travelextras
@user anonymous
@storyId SWACOMTT-998
@not_passing draft

Narrative:
In order to be informed about the different delivery methods
As a user
I want to display the Delivery Methods FAQs when I am on the Create a Card Page.

Scenario: User goes to the Giftcard Landing Page and views the Delivery Methods FAQs on the Create a Card Page

Given I am on the Home page
When I click on the view all travel tools link on the Home Page
And I click on the travel extras link on the Travel Tools Page
And I click on the southwest gift card breadcrumb on the Travel Extras Page
And I click on the continue button on the Gift Card Landing page
Then I see opened the up to 12 hours flyOut on the Create a Card Page
And I see opened the 4-7 business days flyOut on the Create a Card Page
And I see opened the standard overnight flyOut on the Create a Card Page