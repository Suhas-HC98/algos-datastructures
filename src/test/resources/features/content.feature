@content
Feature: Check file content

  Scenario Outline: Check file content as string
    Given user makes API request "/file/fetch-content" with params "<fileName>"
    And response code is 200
    Then data produced is not null
  Examples:
    | fileName       |
    | PDFContent.txt |

  Scenario Outline: Check file content as string
    Given user makes API request "/file/fetch-content" with params "<fileName>"
    And response code is 200
    Then data produced is not null
  Examples:
      | fileName        |