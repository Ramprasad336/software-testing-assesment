@checkout
Feature: Checkout Items from Cart
  Description: Test checkout functionality

  Scenario: Checkout Functionality
    Given User launches chrome browser with URL
    When user enters username "standard_user" and password "secret_sauce"
    When user clicks on login button
    Then user redirects to inventory page
    When User selects any 2 products
    When User click on Add to cart button
    Then Cart should contain 2 items
    When User clicks on Cart button
    Then Cart Details Page is displayed
    And Cart Details contains added items
    And User Verifies added items details
    When User clicks on Checkout button
    Given User in Checkout page
    When User enters firstName "Test"  LastName "TestPay" postalcode "E1 6AN"
    When User clicks on continue button
    Then summary details page is displayed
    And User Verifies total summary "$39.98"
    Given User in Summary page
    When User clicks on Finish button
    Then order confirmation message is displayed

#  Scenario: Add to Cart Details
#    When User selects any 2 products
#    When User click on Add to cart button
#    Then Cart should contain 2 items

#  Scenario: Verify Cart Details
#    When User clicks on Cart button
#    Then Cart Details Page is displayed
#    And Cart Details contains added items
#    And User Verifies added items details
#    When User clicks on Checkout button

#  Scenario: Checkout
#    Given User in Checkout page
#    When User enters firstName "Test"  LastName "TestPay" postalcode "E1 6AN"
#    When User clicks on continue button
#    Then summary details page is displayed
#    And User Verifies total summary "$39.98"

#  Scenario: Order Confirmation
#    Given User in Summary page
#    When User clicks on Finish button
#    Then order confirmation message is displayed
		
		
		
		

		


	

























#Author: your.email@your.domain.com
#Keywords Summary :
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
#@tag
#Feature: Title of your feature
  #I want to use this template for my feature file
#
  #@tag1
  #Scenario: Title of your scenario
    #Given I want to write a step with precondition
    #And some other precondition
    #When I complete action
    #And some other action
    #And yet another action
    #Then I validate the outcomes
    #And check more outcomes
#
  #@tag2
  #Scenario Outline: Title of your scenario outline
    #Given I want to write a step with <name>
    #When I check for the <value> in step
    #Then I verify the <status> in step
#
    #Examples: 
      #| name  | value | status  |
      #| name1 |     5 | success |
      #| name2 |     7 | Fail    |
      

