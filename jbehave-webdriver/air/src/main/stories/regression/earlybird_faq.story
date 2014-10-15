User views Early Bird FAQ page

Meta:
@suite regression
@flow air
@storyID PODIV-603
@dyna_stubs
@Everic
@not_passing draft

Narrative:
In order to verify the earlybird FAQ page
As a user
I want to click the through the learn more about earlybird checkin link on the early bird checkin page


Scenario: User verifies Early Bird FAQs page

Given I am on the early bird page
When I click the learn more about earlybird checkin link
Then I will see the earlybird faqs page
When I change the topic to advertised fare
And I click the Go button
Then The faq topic displays as advertised fare
When I change the topic to earlybird checkin
And I click the Go button
Then I will see the earlybird faqs page
