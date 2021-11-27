@rest-api
Feature: Testing a REST API with page template-8 (empty file extension)
  Users should be able to send POST, GET, DELETE requests to a web service to upload/fetch/delete/install page templates

  Scenario: Page template (template_8) uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'template_8'
    When users upload page template
    Then upload operation should fail with status (400) and message 'Allowed file types are {\'yml\', \'yaml\'}'

  Scenario: Page templates retrieved from a web service
    Given users want to fetch uploaded page templates
    When users request information on the uploaded page templates
    Then page templates data 'template_8.json' should be successfully returned

  Scenario: Page template (template_8) installed on a web service
    Given users want to install uploaded page template with id 'template_8'
    When users install uploaded page template
    Then install operation should fail with page template 'template_8' not found

  Scenario: Page template (template_8) deleted from a web service
    Given users want to delete uploaded page template with id 'template_8'
    When users delete information on the uploaded page template
    Then delete operation should fail as page template with id 'template_8' should not be found
