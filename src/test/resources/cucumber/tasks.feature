Feature: Task

    Scenario: Create new task
        When I POST task dto with title 'TAJTL'
        Then New ACTIVE task is created with title 'TAJTL'

    Scenario: Try to create new task with empty title
        When I POST task dto with title ''
        Then task is not created

# ACTIVE, READY, ALMOST_READY, FINISHED, CANCELED
    Scenario: Approve ACTIVE task
        Given task is in status 'ACTIVE'
        When Command 'approve' is sent
        Then Command is accepted
        And Task is approved
        And Status is 'READY'

    Scenario: Reject ACTIVE task
        Given task is in status 'ACTIVE'
        When Command 'reject' is sent
        Then Command is accepted
        And Task is rejected
        And Status is 'ALMOST_READY'

    Scenario: Complete ACTIVE task
        Given task is in status 'ACTIVE'
        When Command 'finish' is sent
        Then Command is accepted
        And Status is 'ACTIVE'

    Scenario: Make ready ACTIVE task
        Given task is in status 'ACTIVE'
        When Command 'make-ready' is sent
        Then Command is accepted
        And Status is 'ACTIVE'

    Scenario: Cancel ACTIVE task
        Given task is in status 'ACTIVE'
        When Command 'cancel' is sent
        Then Command is accepted
        And Status is 'CANCELED'

    Scenario: Rename ACTIVE task
        Given task is in status 'ACTIVE'
        Given title is 'TAJTL'
        When Tile is changed to 'NEW TITLE'
        Then Command is accepted
        And Status is 'ACTIVE'
        And title is 'NEW TITLE'

    Scenario: Approve READY task
        Given task is in status 'READY'
        When Command 'approve' is sent
        Then Command is accepted
        And Task is approved
        And Status is 'READY'

    Scenario: Reject READY task
        Given task is in status 'READY'
        When Command 'reject' is sent
        Then Command is accepted
        And Task is approved
        And Status is 'READY'

    Scenario: Complete READY task
        Given task is in status 'READY'
        When Command 'finish' is sent
        Then Command is accepted
        And Task is approved
        And Status is 'FINISHED'

    Scenario: Cancel READY task
        Given task is in status 'READY'
        When Command 'cancel' is sent
        Then Command is accepted
        And Task is approved
        And Status is 'CANCELED'


  Scenario: Make ready READY task
    Given task is in status 'READY'
    When Command 'make-ready' is sent
    Then Command is accepted

  Scenario: Approve ALMOST_READY task
    Given task is in status 'ALMOST_READY'
    When Command 'approve' is sent
    Then Command is accepted

  Scenario: Reject ALMOST_READY task
    Given task is in status 'ALMOST_READY'
    When Command 'reject' is sent
    Then Command is accepted

  Scenario: Complete ALMOST_READY task
    Given task is in status 'ALMOST_READY'
    When Command 'finish' is sent
    Then Command is accepted

  Scenario: Cancel ALMOST_READY task
    Given task is in status 'ALMOST_READY'
    When Command 'cancel' is sent
    Then Command is accepted

  Scenario: Approve CANCELED task
    Given task is in status 'CANCELED'
    When Command 'approve' is sent
    Then 'IllegalStateException' is thrown

  Scenario: Reject CANCELED task
    Given task is in status 'CANCELED'
    When Command 'reject' is sent
    Then 'IllegalStateException' is thrown

  Scenario: Complete CANCELED task
    Given task is in status 'CANCELED'
    When Command 'finish' is sent
    Then 'IllegalStateException' is thrown

  Scenario: Cancel CANCELED task
    Given task is in status 'CANCELED'
    When Command 'cancel' is sent
    Then 'IllegalStateException' is thrown

  Scenario: Approve COMPLETED task
    Given task is in status 'CANCELED'
    When Command 'approve' is sent
    Then 'IllegalStateException' is thrown

  Scenario: Reject COMPLETED task
    Given task is in status 'CANCELED'
    When Command 'reject' is sent
    Then 'IllegalStateException' is thrown

  Scenario: Complete COMPLETED task
    Given task is in status 'CANCELED'
    When Command 'finish' is sent
    Then 'IllegalStateException' is thrown

  Scenario: Cancel COMPLETED task
    Given task is in status 'CANCELED'
    When Command 'cancel' is sent
    Then 'IllegalStateException' is thrown
