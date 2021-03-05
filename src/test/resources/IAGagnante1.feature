Feature:
  Scenario:
    Given The grid contains "X" at "A"
    And The grid contains "X" at "G"
    And The grid contains "X" at "B"
    And The grid contains "O" at "C"
    And The grid contains "O" at "E"
    And The grid contains "O" at "F"
    When Player "O" plays
    Then He take place in "D", Player "O" Win the first game
