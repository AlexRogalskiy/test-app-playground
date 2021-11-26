@rest-api9
Feature: Testing a REST API with page template-9 (non-existent parent)
  Users should be able to send POST, GET, DELETE requests to a web service to upload/fetch/delete/install page templates

  Scenario: Page template (template_9) uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'src/test/resources/templates/request/template_9.yaml'
    When users upload page template
    Then page template with id 'template_9' should be successfully uploaded

  Scenario: Page templates retrieved from a web service
    Given users want to fetch uploaded page templates
    When users request information on the uploaded page templates
    Then page templates data 'src/test/resources/templates/response/template_9.json' should be successfully returned

  Scenario: Page template (template_9) installed on a web service
    Given users want to install uploaded page template with id 'template_9'
    When users install uploaded page template
    Then install operation should fail with status (400) and message 'Dependency form {\'id\': \'element-5\', \'label\': \'Element 5\', \'link\': \'http://element-5.ru\', \'depends\': \'element-0\'} is not presented in template'

  Scenario: Page template (template_9) deleted from a web service
    Given users want to delete uploaded page template with id 'template_9'
    When users delete information on the uploaded page template
    Then page template with id 'template_9' should be successfully deleted
