Feature: Application Login

Scenario: Login with valid crediential
Given Open any browser
And Navigate to Login Page
When User enter username as "test_admin@gmail.com" and password as "3456" into the fields
And User clicks on Login button
Then Verify user is able to successfully login