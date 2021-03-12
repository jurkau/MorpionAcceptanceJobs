Feature:
  Scenario: Victoire de l'IA après une lutte acharné
    Given The grid contains "X" at "A"
    And The grid contains "O" at "G"
    And The grid contains "O" at "B"
    And The grid contains "X" at "C"
    And The grid contains "X" at "E"
    And The grid contains "X" at "F"
    When Player "X" plays
    Then He take place in "D", Player "X" Win the first game


  Scenario: Match nul entre l'IA et le joueur humain
    Given The grid contains "X" at "A"
    And The grid contains "O" at "B"
    And The grid contains "X" at "D"
    And the grid contains "O" at "G"
    And the grid contains "X" at "E"
    And the grid contains "O" at "I"
    And the grid contains "X" at "F"
    And the grid contains "O" at "C"
    When Player "X" plays
    Then He take place in "H", it's a tie between the two player