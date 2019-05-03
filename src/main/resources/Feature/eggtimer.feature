Feature: Verify egg timer portal

  Scenario: Verify the timer by providing the time in seconds
    Given Load the portal url 'eggTimer'
    When Pass the time '10' seconds and click on GO!
    And Count the timer while decreasing the seconds
    Then Display the message after time expiry
