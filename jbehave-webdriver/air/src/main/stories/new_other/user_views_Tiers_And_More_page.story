View the benefits for A-List

Meta:
@flow other
@user anonymous
@dyna_stubs

Narrative:
In order to know the benefits for A-List, A-List Preferred and Companion Pass qualified members
As an Anonymous User
I want to see "Tiers & More" page

Scenario: User views "Tiers & More" page
Given I am a Southwest User at How to earn page
When I access Tiers and More page
Then I see the A List breadcrumb link on the Tiers & More page
And I see the A-List Preferred breadcrumb link on the Tiers & More page
And I see the Companion Pass breadcrumb link on the Tiers & More page
And I see A-List card is selected
And I see the A-List Status title
And I see the A-List Program Rules subtitle
When I select A-List Preferred card
Then I see the Tiers More Alist Preferred page
And I see the A List breadcrumb link on the Tiers & More page
And I see the A-List Preferred breadcrumb link on the Tiers & More page
And I see the Companion Pass breadcrumb link on the Tiers & More page
And I see A-List Preferred card is selected
And I see the A-List Preferred Status title
And I see the A-List Preferred Program Rules subtitle
When I select Companion Pass card
Then I see the Tiers More Companion Pass page
And I see the A List breadcrumb link on the Tiers & More page
And I see the A-List Preferred breadcrumb link on the Tiers & More page
And I see the Companion Pass breadcrumb link on the Tiers & More page
And I see Companion Pass card is selected
And I see the Companion Pass Status title
And I see the Companion Pass Program Rules subtitle