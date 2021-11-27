@rest-api
Feature: Testing a REST API with page template-1
  Users should be able to send POST, GET, DELETE requests to a web service to upload/fetch/delete/install page templates

  Scenario: Page template (template_1) uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'template_1.yaml'
    When users upload page template
    Then page template with id 'template_1' should be successfully uploaded

  Scenario: Page templates retrieved from a web service
    Given users want to fetch uploaded page templates
    When users request information on the uploaded page templates
    Then page templates data 'template_1.json' should be successfully returned

  Scenario: Page template (template_1) installed on a web service
    Given users want to install uploaded page template with id 'template_1'
    When users install uploaded page template
    Then page template with id 'template_1' should be successfully installed

  Scenario: Page template (template_1) rendered on a web service
    Given users want to view installed page template on a web page
    And users want to compare with file template 'template_1.yaml'
    When users navigate to web page
    Then page template should be correctly rendered

  Scenario: Page template (template_1) deleted from a web service
    Given users want to delete uploaded page template with id 'template_1'
    When users delete information on the uploaded page template
    Then page template with id 'template_1' should be successfully deleted
