@rest-api
Feature: Testing a REST API with page template-5 (invalid file format)
  Users should be able to send POST, GET, DELETE requests to a web service to upload/fetch/delete/install page templates

  Scenario: Page template (template_5) uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'template_5.pdf'
    When users upload page template
    Then upload operation should fail with status (400) and message 'Allowed file types are '

  Scenario: Page templates retrieved from a web service
    Given users want to fetch uploaded page templates
    When users request information on the uploaded page templates
    Then page templates data 'template_5.json' should be successfully returned

  Scenario: Page template (template_5) installed on a web service
    Given users want to install uploaded page template with id 'template_5'
    When users install uploaded page template
    Then install operation should fail with page template 'template_5' not found

  Scenario: Page template (template_5) deleted from a web service
    Given users want to delete uploaded page template with id 'template_5'
    When users delete information on the uploaded page template
    Then delete operation should fail as page template with id 'template_5' should not be found
