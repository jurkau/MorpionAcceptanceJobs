Feature:
  Scenario:
    Given The grid contains "X" at "A"
    And The grid contains "X" at "B"
    And The grid contains "O" at "D"
    When Player "O" plays
    Then He take place at "C"