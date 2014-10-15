I Need My Password Flow - swabiz

Meta:
@suite GetHealthy
@flow Change Email Flow
@process Change Email
@user Swabiz Regular Member / Swabiz Regular Customer
@storyId PODVI-57 PODVI-63 PODVI-59 PODVI-73
@not_passing draft

Narrative:
In order to change my account email
As a rapid reward customer or as rapid rewards member
I want to change my e-mail completing the Has Your E-mail been changed? flow with my account information
starting from I Need My Username/Account number tab on swabiz site

Scenario: User changes accounts e-mail

Given I am on Has Your e-mail Been Changed Page from swabiz I Need My Username/Account Number entry point
When I complete the Change e-mail fields
Then I see the Old e-mail page
When I complete the Old e-mail field
Then I see the Security Questions page
When I complete the Security Question page
Then I see the New e-mail page
When I complete the New e-mail fields
Then I see the Change e-mail confirmation page
