@rest-api
Feature: Testing a REST API with page template-15 (empty template)
  Users should be able to send POST, GET, DELETE requests to a web service to upload/fetch/delete/install page templates

  Scenario: Page template (template_15) uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'template_15.yaml'
    When users upload page template
    Then page template with id 'template_15' should be successfully uploaded

  Scenario: Page templates retrieved from a web service
    Given users want to fetch uploaded page templates
    When users request information on the uploaded page templates
    Then page templates data 'template_15.json' should be successfully returned

  Scenario: Page template (template_15) installed on a web service
    Given users want to install uploaded page template with id 'template_15'
    When users install uploaded page template
    Then install operation should fail with status (500)

  Scenario: Page template (template_15) deleted from a web service
    Given users want to delete uploaded page template with id 'template_15'
    When users delete information on the uploaded page template
    Then page template with id 'template_15' should be successfully deleted
