@rest-api6
Feature: Testing a REST API with page template-6
  Users should be able to send POST, GET, DELETE requests to a web service to upload/fetch/delete/install page templates

  Scenario: Page template (template_6) uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'src/test/resources/templates/request/template_6.yaml'
    When users upload page template
    Then page template with id 'template_6' should be successfully uploaded

  Scenario: Page templates retrieved from a web service
    Given users want to fetch uploaded page templates
    When users request information on the uploaded page templates
    Then page templates data 'src/test/resources/templates/response/template_6.json' should be successfully returned

  Scenario: Page template (template_6) installed on a web service
    Given users want to install uploaded page template with id 'template_6'
    When users install uploaded page template
    Then page template with id 'template_6' should be successfully installed

  Scenario: Page template (template_6) deleted from a web service
    Given users want to delete uploaded page template with id 'template_6'
    When users delete information on the uploaded page template
    Then page template with id 'template_6' should be successfully deleted
