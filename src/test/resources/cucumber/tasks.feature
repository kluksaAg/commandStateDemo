Feature: Task

  Scenario: Create new task
    When I POST task dto with title 'TAJTL'
    Then New ACTIVE task is created with title 'TAJTL'

  Scenario: Try to create new task with empty title
    When I POST task dto with title ''
    Then task is not created

  Scenario Outline: Valjani rename title
    Given task is in status '<status>'
    Given title is 'TAJTL'
    When Tile is changed to '<new_title>'
    Then Command is accepted
    And Status is '<status>'
    And title is '<new_title>'

    Examples:
      | status    | new_title |
      | ACTIVE    | AKTIVAN   |
      | NOT_READY | Nespreman |
      | READY     | Spreman   |

  Scenario Outline: Nevaljani rename title
    Given task is in status '<status>'
    Given title is 'TAJTL'
    When Tile is changed to '<new_title>'
    Then 'IllegalStateException' is thrown

    Examples:
      | status    | new_title |
      | CANCELED  | AKTIVAN   |
      | COMPLETED | Nespreman |

  Scenario Outline: Validni prelazi
    Given task is in status '<status>'
    When Command '<command>' is sent
    Then Command is accepted
    And Approved is '<approved>'
    And Status is '<new_status>'

    Examples:
      | status    |  | command  | new_status | approved |
      | ACTIVE    |  | approve  | READY      | true     |
      | ACTIVE    |  | reject   | NOT_READY  | false    |
      | ACTIVE    |  | complete | ACTIVE     | NULL     |
      | ACTIVE    |  | cancel   | CANCELED   | NULL     |
      | READY     |  | approve  | READY      | true     |
      | READY     |  | reject   | READY      | true     |
      | READY     |  | complete | COMPLETED  | true     |
      | READY     |  | cancel   | CANCELED   | true     |
      | NOT_READY |  | approve  | READY      | true     |
      | NOT_READY |  | reject   | NOT_READY  | false    |
      | NOT_READY |  | complete | NOT_READY  | false    |
      | NOT_READY |  | cancel   | CANCELED   | false    |
      | COMPLETED |  | complete | COMPLETED  | true     |
      | CANCELED  |  | cancel   | CANCELED   |          |

  Scenario Outline: Nevalidni prelazi
    Given task is in status '<status>'
    When Command '<command>' is sent
    Then 'IllegalStateException' is thrown

    Examples:
      | status    | command  |
      | CANCELED  | approve  |
      | CANCELED  | reject   |
      | CANCELED  | complete |
      | COMPLETED | approve  |
      | COMPLETED | reject   |
      | COMPLETED | cancel   |

