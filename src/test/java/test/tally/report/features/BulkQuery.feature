Feature: Get GL Revenue Recognition v3 data from PROD Kibana

  @FetchGLRevenueRecognitionV3Data
  Scenario: Fetch required data from PROD Kibana
    When requested to search for bulk transactions,
    Then retrieve the necessary data
    And create a table to organize the information.