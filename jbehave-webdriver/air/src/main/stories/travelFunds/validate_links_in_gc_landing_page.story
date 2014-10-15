User validates the links located in the GC Landing Page

Meta:
@flow travel extras
@process view
@suite regression
@travelextras
@user anonymous
@traveler adult
@storyId SWACOMTT-995
@not_passing flaky


Narrative:
In order to be redirected to the Security, FAQ's and Terms & Conditions Pages related to the Giftcards
As a southwest.com user
I want to arrive at the Giftcard Landing Page and access to those pages by using the links located in it.


Scenario: User navigates to the Giftcard Landing Page and uses its links

Given I am on the Home page
When I click on the southwestgiftcard option from the Global Nav Header
Then I see the options of the breadcrumb on the giftcard landing page
And I see three tabs and the Buy a soutwest giftcard tab selected by default on the giftcard landing page
And I see the links Security, FAQs and Terms and Conditions on the giftcard landing page
When I click on the security link on the giftcard landing page
Then I see the security giftcard page
When I click on the FAQs link on the giftcard landing page
Then I see the FAQs giftcard page
When I click on the term and conditions link on the giftcard landing page
Then I see the term and conditions giftcard page