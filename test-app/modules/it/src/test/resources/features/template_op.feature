@rest-api1
Feature: Testing a REST API with page template upload/fetch/delete/install operations
  Users should be able to send POST, GET, DELETE requests to a web service to upload/fetch/delete/install page templates

  Scenario: Page template retrieved from a web service
    Given users want to fetch uploaded page templates
    When users get information on the uploaded page templates
    Then the page templates data is returned 'src/test/resources/templates/response/template_op_1.json'

  Scenario: Page template deleted from a web service
    Given users want to delete uploaded page template 'template_1'
    When users delete information on the uploaded page template
    Then delete operation should fail with page template 'template_1' not found

  Scenario: Page template installed on a web service
    Given users want to install uploaded page template 'template_1'
    When users install uploaded page template
    Then install operation should fail with page template 'template_1' not found

  Scenario: Page template uploaded to a web service
    Given users want to upload page template 'src/test/resources/templates/request/template_1.yaml'
    When users upload page template
    Then upload operation should succeed with status (201) for page template 'template_1'

  Scenario: Page template retrieved from a web service
    Given users want to fetch uploaded page templates
    When users get information on the uploaded page templates
    Then the page templates data is returned 'src/test/resources/templates/response/template_op_2.json'

  Scenario: Page template installed on a web service
    Given users want to install uploaded page template 'template_1'
    When users install uploaded page template
    Then install operation should succeed with status (200) for page template 'template_1'

  Scenario: Page template deleted from a web service
    Given users want to delete uploaded page template 'template_1'
    When users delete information on the uploaded page template
    Then the page template 'template_1' is deleted

  Scenario: Page template with alphabetic id uploaded to a web service
    Given users want to upload page template 'src/test/resources/templates/request/template_1.yaml'
    When users upload page template with id 'my_custom_id'
    Then upload operation should succeed with status (201) for page template 'my_custom_id'

  Scenario: Page template with blank id uploaded to a web service
    Given users want to upload page template 'src/test/resources/templates/request/template_1.yaml'
    When users upload page template with id ' 123 '
    Then upload operation should succeed with status (201) for page template ' 123 '

  Scenario: Page template with invalid id uploaded to a web service
    Given users want to upload page template 'src/test/resources/templates/request/template_1.yaml'
    When users upload page template with id '$*'
    Then upload operation should succeed with status (201) for page template '$*'

  Scenario: Page template with numeric id uploaded to a web service
    Given users want to upload page template 'src/test/resources/templates/request/template_1.yaml'
    When users upload page template with id '-0'
    Then upload operation should succeed with status (201) for page template '-0'

  Scenario: Page template with squared id uploaded to a web service
    Given users want to upload page template 'src/test/resources/templates/request/template_1.yaml'
    When users upload page template with id '<>'
    Then upload operation should succeed with status (201) for page template '<>'

  Scenario: Page template installed on a web service
    Given users want to install uploaded page template '-0'
    When users install uploaded page template
    Then install operation should succeed with status (200) for page template '-0'

  Scenario: Page template installed on a web service
    Given users want to install uploaded page template '<>'
    When users install uploaded page template
    Then install operation should succeed with status (200) for page template '<>'

  Scenario: Page template retrieved from a web service
    Given users want to fetch uploaded page templates
    When users get information on the uploaded page templates
    Then the page templates data is returned 'src/test/resources/templates/response/template_op_3.json'

  Scenario: Page template with lengthy id uploaded to a web service
    Given users want to upload page template 'src/test/resources/templates/request/template_1.yaml'
    When users upload page template with id 'src/test/resources/templates/template_1.yaml'
    Then upload operation should fail with status (500)

  Scenario: Page template deleted from a web service
    Given users want to delete uploaded page template 'my_custom_id'
    When users delete information on the uploaded page template
    Then the page template 'my_custom_id' is deleted

  Scenario: Page template deleted from a web service
    Given users want to delete uploaded page template ' 123 '
    When users delete information on the uploaded page template
    Then the page template ' 123 ' is deleted

  Scenario: Page template deleted from a web service
    Given users want to delete uploaded page template '<>'
    When users delete information on the uploaded page template
    Then the page template '<>' is deleted

  Scenario: Page template deleted from a web service
    Given users want to delete uploaded page template '-0'
    When users delete information on the uploaded page template
    Then the page template '-0' is deleted

  Scenario: Page template deleted from a web service
    Given users want to delete uploaded page template '$*'
    When users delete information on the uploaded page template
    Then the page template '$*' is deleted
