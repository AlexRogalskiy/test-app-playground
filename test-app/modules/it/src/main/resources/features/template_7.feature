@rest-api1
Feature: Testing a REST API with page template-7 (invalid template format)
  Users should be able to send POST, GET, DELETE requests to a web service to upload/fetch/delete/install page templates

  Scenario: Page template (template_7) uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'template_7.yaml'
    When users upload page template
    Then page template with id 'template_7' should be successfully uploaded

  Scenario: Page templates retrieved from a web service
    Given users want to fetch uploaded page templates
    When users request information on the uploaded page templates
    Then page templates data 'template_7.json' should be successfully returned

  Scenario: Page templates (template_7) installed on a web service
    Given users want to install uploaded page template with id 'template_7'
    When users install uploaded page template
    Then install operation should fail with status (400) and message 'Invalid template format!'

  Scenario: Page template (template_7) deleted from a web service
    Given users want to delete uploaded page template with id 'template_7'
    When users delete information on the uploaded page template
    Then page template with id 'template_7' should be successfully deleted
