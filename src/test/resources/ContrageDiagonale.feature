Feature:
  Scenario:
    Given The grid contains "X" at "A"
    And The grid contains "X" at "E"
    And  The grid contains "O" at "F"
    When Player "O" plays
    Then He take place on "I"