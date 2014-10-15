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
I want to change my e-mail completing the Has Your E-mail been changed? page with my account information
starting from I Need My Password number tab on Swabiz site

Scenario: User changes accounts e-mail

Given I am on Has Your E-mail Been Changed Page from Swabiz I Need My Password entry point
When I complete the Change e-mail fields
Then I see the Old e-mail page
When I complete the Old e-mail field
Then I see the Security Questions page
When I complete the Security Question page
Then I see the New e-mail page
When I complete the New e-mail fields
Then I see the Change e-mail confirmation page
