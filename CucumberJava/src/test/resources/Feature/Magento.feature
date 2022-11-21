#Author: chinzaa
#Keywords Summary : This is a sample BDD project based on Cucumber and Java
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: feature to test login functionality


Background:
		Given user is on home page and clicks create account

@test
Scenario: Fetch User details
	When randomUser api gives response

@test
  Scenario: Check login is successful with valid credentials
    When user enters valid fname and lname and mail and password and creates account
    And user fetches the account details for email
    Then user clicks Edit Address to add shipping address
    When add item to cart for size "medium"
    And checkout item in cart and validate cart count
    And validate purchase success message
    
@test
	Scenario: Checks cannot create account with already existing email
	When user enters already existing mail and views and validates the error message