Feature: Testing different request on the booking application

  Scenario: Check if the booking application can be accessed by users
    When User sends a GET request to booking endpoint
    Then User must get back a valid status code 200

  Scenario: Verify if the  response is correct from server by booking/10
    When User sends a GET request for booking/10 endpoint
    Then User must get back a valid status code 200

  Scenario: If User can update details of booking by bookingId
    When User update booking details of bookingId 10 endpoint
    Then User must get back a valid status code 200

  Scenario: If User can delete booking details by bookingId
    When User delete booking details of bookingId 10 endpoint
    Then User must get back a valid status code 204





