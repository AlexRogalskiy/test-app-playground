@rest-api5
Feature: Testing a REST API with page template-5 (invalid file format)
  Users should be able to send POST, GET, DELETE requests to a web service to upload/fetch/delete/install page templates

  Scenario: Page template uploaded to a web service
    Given users want to upload page template 'src/test/resources/templates/request/template_5.pdf'
    When users upload page template
    Then upload operation should fail with status (400) and message 'Allowed file types are {\'yaml\', \'yml\'}'

  Scenario: Page template retrieved from a web service
    Given users want to fetch uploaded page templates
    When users get information on the uploaded page templates
    Then the page templates data is returned 'src/test/resources/templates/response/template_5.json'

  Scenario: Page template installed on a web service
    Given users want to install uploaded page template 'template_5'
    When users install uploaded page template
    Then install operation should fail with page template 'template_5' not found

  Scenario: Page template deleted from a web service
    Given users want to delete uploaded page template 'template_5'
    When users delete information on the uploaded page template
    Then delete operation should fail with page template 'template_5' not found
