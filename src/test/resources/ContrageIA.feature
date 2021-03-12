Feature: Stratégie de contrage de l'IA quand elle a encore une chance de gagner

  Scenario: contrage en digaonale numéro 1
    Given The grid contains "X" at "A"
    And The grid contains "X" at "E"
    And  The grid contains "O" at "F"
    When Player "O" plays
    Then He take place on "I"

  Scenario: contrage en diagonale numéro 2
    Given The grid contains "X" at "A"
    And The grid contains "X" at "B"
    And The grid contains "O" at "D"
    When Player "O" plays
    Then He take place at "C"


  Scenario: contrage en ligne numéro 1
    Given
