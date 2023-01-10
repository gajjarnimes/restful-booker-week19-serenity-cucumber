Feature: Herokuapp Booker Application
  As a user I want to test herokuapp boker application

  Scenario Outline: CRUD Test
    Given URL is given
    When I create booking with firstName "<firstName>"  lastName"<lastName>" totalPrice"<totalPrice>" depositPaid"<depositPaid>" checkin"<checkin>" checkout"<checkout>" additionalNeeds"<additionalNeeds>"
    Then I verify that booking is created
    And I update booking with firstName "<firstName>"  lastName"<lastName>" totalPrice"<totalPrice>" depositPaid"<depositPaid>" checkin"<checkin>" checkout"<checkout>" additionalNeeds"<additionalNeeds>"
    And I check user updated successfully
    And I delete booking with bookingId
    Then I verify that booking is deleted successfully
    Examples:
      | firstName | lastName | totalPrice | depositPaid | checkin    | checkout   | additionalNeeds |
      | Keshav    | Narayan    | 500        | true        | 2022-10-07 | 2022-11-20 | Breakfast       |