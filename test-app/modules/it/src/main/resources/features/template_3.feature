@rest-api
Feature: Testing a REST API with page template-3 (invalid parent id dependency)
  Users should be able to send POST, GET, DELETE requests to a web service to upload/fetch/delete/install page templates

  Scenario: Page template (template_3) uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'template_3.yml'
    When users upload page template
    Then page template with id 'template_3' should be successfully uploaded

  Scenario: Page templates retrieved from a web service
    Given users want to fetch uploaded page templates
    When users request information on the uploaded page templates
    Then page templates data 'template_3.json' should be successfully returned

  Scenario: Page template (template_3) installed on a web service
    Given users want to install uploaded page template with id 'template_3'
    When users install uploaded page template
    Then install operation should fail with status (400) and message 'Dependency form {\'id\': 3, \'label\': \'Element 3\', \'link\': \'http://element-3.ru\', \'depends\': 2} is not presented in template'

  Scenario: Page template (template_3) deleted from a web service
    Given users want to delete uploaded page template with id 'template_3'
    When users delete information on the uploaded page template
    Then page template with id 'template_3' should be successfully deleted
