Feature: Instructor sign up
  Scenario: create to pilot and instructor profiles
    When instructor signs up
    Then there is a pilot profile created

  Scenario: Attempted to sign up with email that already exists
    Given an instructor is in the database
    When instructor signs up
    Then a 409 status is returned

  Scenario: Logging in
    Given an instructor is in the database
    When the instructor logs in
    Then a 200 status is returned

  Scenario: Logging in with incorrect password
    Given an instructor is in the database
    When the instructor logs in with an incorrect password
    Then a 401 status is returned

  Scenario: Reset password sends password reset email
    Given an instructor is in the database
    When the instructor forgets their password
    Then a password reset email is sent