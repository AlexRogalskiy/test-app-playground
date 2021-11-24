@rest-api3
Feature: Testing a REST API with page template-3 (invalid parent id dependency)
  Users should be able to send POST, GET, DELETE requests to a web service to upload/fetch/delete/install page templates

  Scenario: Page template uploaded to a web service
    Given users want to upload page template 'src/test/resources/templates/request/template_3.yml'
    When users upload page template
    Then upload operation should succeed with status (201) for page template 'template_3'

  Scenario: Page template retrieved from a web service
    Given users want to fetch uploaded page templates
    When users get information on the uploaded page templates
    Then the page templates data is returned 'src/test/resources/templates/response/template_3.json'

  Scenario: Page template installed on a web service
    Given users want to install uploaded page template 'template_3'
    When users install uploaded page template
    Then install operation should fail with status (400) and message 'Dependency form {\'id\': 3, \'label\': \'Element  3\', \'link\': \'http://rambler.ru\', \'depends\': 2} is not presented in template'

  Scenario: Page template deleted from a web service
    Given users want to delete uploaded page template 'template_3'
    When users delete information on the uploaded page template
    Then the page template 'template_3' is deleted