Feature: Stratégie de contrage de l'IA quand elle a encore une chance de gagner

  Scenario: contrage en digaonale numéro 1
    Given The grid contains "O" at "A"
    And The grid contains "O" at "E"
    And  The grid contains "X" at "F"
    When Player "X" plays
    Then He take place on "I"

  Scenario: contrage en diagonale numéro 2
    Given The grid contains "O" at "A"
    And The grid contains "O" at "B"
    And The grid contains "X" at "D"
    When Player "X" plays
    Then He take place at "C"


  Scenario: contrage en ligne numéro 2 en ligne
    Given The grid contains "X" at "E"
    And The grid contains "O" at "A"
    And The grid contains "O" at "G"
    And The grid contains "O" at "F"
    When Player "X" plays
    Then He take place at "D"


  Scenario: contrage en ligne numéro 1 en ligne
    Given The grid contains "X" at "A"
    And The grid contains "X" at "B"
    And The grid contains "O" at "C"
    And The grid contains "O" at "F"
    When Player "X" plays
    Then He take place at "I"



  Scenario: Contre a la ligne numéro 2 en diagonale
    Given The grid contains "O" at "D"
    And The grid contains "O" at "F"
    And The grid contains "X" at "I"
    When Player "X" plays
    Then He take place at "E"