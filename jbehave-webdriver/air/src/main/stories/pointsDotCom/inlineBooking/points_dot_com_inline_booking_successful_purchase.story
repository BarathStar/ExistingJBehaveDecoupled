Verify inline booking when points toggle is on

Meta:
@suite pointsDotCom
@flow in_path_booking
@process loyalty
@user rr_member
@traveler adult
@project points_dot_com
@POINTSDOTCOM_BUY_GIFT_TRANSFER toggle is ON
@dyna_stubs
@not_passing incorrect_modal_detection

Narrative:
In order to book a flight with points
As a Rapid Rewards Member without enough points
I want to attempt to book a flight

Scenario: Rapid Rewards Member air purchase when Points.com toggle is ON
Given I am flying a round-trip Southwest Southwest flight
And I am logged in as a Alist Rapid Rewards member on the Search Flights page
When I book a flight with points
Then I should receive an air confirmation