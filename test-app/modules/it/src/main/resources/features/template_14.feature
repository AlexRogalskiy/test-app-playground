@rest-api
Feature: Testing a REST API with page template-14 (multiple parent elements)
  Users should be able to send POST, GET, DELETE requests to a web service to upload/fetch/delete/install page templates

  Scenario: Page template (template_14) uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'template_14.yaml'
    When users upload page template
    Then page template with id 'template_14' should be successfully uploaded

  Scenario: Page templates retrieved from a web service
    Given users want to fetch uploaded page templates
    When users request information on the uploaded page templates
    Then page templates data 'template_14.json' should be successfully returned

  Scenario: Page template (template_14) installed on a web service
    Given users want to install uploaded page template with id 'template_14'
    When users install uploaded page template
    Then page template with id 'template_14' should be successfully installed

  Scenario: Page template (template_14) rendered on a web service
    Given users want to view installed page template on a web page
    And users want to compare with file template 'template_14.yaml'
    When users navigate to web page
    Then page template should be correctly rendered

  Scenario: Page template (template_14) deleted from a web service
    Given users want to delete uploaded page template with id 'template_14'
    When users delete information on the uploaded page template
    Then page template with id 'template_14' should be successfully deleted
