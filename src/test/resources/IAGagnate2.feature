Feature:
  Scenario:
    Given The grid contains "X" at "A"
    And The grid contains "O" at "E"
    And The grid contains "X" at "B"
    And The grid contains "O" at "C"
    And The grid contains "X" at "D"
    When Player "O" plays
    Then He take place in "G", Player "O" win the game