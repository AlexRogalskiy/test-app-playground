@rest-api0
Feature: Testing a REST API with page template upload/fetch/delete/install operations
  Users should be able to send POST, GET, DELETE requests to a web service to upload/fetch/delete/install page templates

  Scenario: Page templates retrieved from a web service
    Given users want to fetch uploaded page templates
    When users request information on the uploaded page templates
    Then page templates data 'src/test/resources/templates/response/template_base_1.json' should be successfully returned

  Scenario: Page template (template_1) deleted from a web service
    Given users want to delete uploaded page template with id 'template_1'
    When users delete information on the uploaded page template
    Then delete operation should fail as page template with id 'template_1' should not be found

  Scenario: Page template (template_1) installed on a web service
    Given users want to install uploaded page template with id 'template_1'
    When users install uploaded page template
    Then install operation should fail with page template 'template_1' not found

  Scenario: Page template (template_1) uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'src/test/resources/templates/request/template_1.yaml'
    When users upload page template
    Then page template with id 'template_1' should be successfully uploaded

  Scenario: Page templates retrieved from a web service
    Given users want to fetch uploaded page templates
    When users request information on the uploaded page templates
    Then page templates data 'src/test/resources/templates/response/template_base_2.json' should be successfully returned

  Scenario: Page template (template_1) installed on a web service
    Given users want to install uploaded page template with id 'template_1'
    When users install uploaded page template
    Then page template with id 'template_1' should be successfully installed

  Scenario: Page template (template_1) deleted from a web service
    Given users want to delete uploaded page template with id 'template_1'
    When users delete information on the uploaded page template
    Then page template with id 'template_1' should be successfully deleted

  Scenario: Page template (template_1) with alphabetic id uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'src/test/resources/templates/request/template_1.yaml'
    And users provide page template id 'my_custom_id'
    When users upload page template
    Then page template with id 'my_custom_id' should be successfully uploaded

  Scenario: Page template (template_1) with spaced id uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'src/test/resources/templates/request/template_1.yaml'
    And users provide page template id ' 123 '
    Then page template with id ' 123 ' should be successfully uploaded

  Scenario: Page template (template_1) with invalid id uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'src/test/resources/templates/request/template_1.yaml'
    And users provide page template id '$*'
    Then page template with id '$*' should be successfully uploaded

  Scenario: Page template (template_1) with numeric id uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'src/test/resources/templates/request/template_1.yaml'
    And users provide page template id '-0'
    Then page template with id '-0' should be successfully uploaded

  Scenario: Page template (template_1) with squared id uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'src/test/resources/templates/request/template_1.yaml'
    And users provide page template id '<>'
    Then page template with id '<>' should be successfully uploaded

  Scenario: Page template ('-0') installed on a web service
    Given users want to install uploaded page template with id '-0'
    When users install uploaded page template
    Then page template with id '-0' should be successfully installed

  Scenario: Page template ('<>') installed on a web service
    Given users want to install uploaded page template with id '<>'
    When users install uploaded page template
    Then page template with id '<>' should be successfully installed

  Scenario: Page templates retrieved from a web service
    Given users want to fetch uploaded page templates
    When users request information on the uploaded page templates
    Then page templates data 'src/test/resources/templates/response/template_base_3.json' should be successfully returned

  Scenario: Page template (template_1) with lengthy id uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'src/test/resources/templates/request/template_1.yaml'
    And users provide page template id 'src/test/resources/templates/template_1.yaml'
    Then upload operation should fail with status (500)

  Scenario: Page template (my_custom_id) deleted from a web service
    Given users want to delete uploaded page template with id 'my_custom_id'
    When users delete information on the uploaded page template
    Then page template with id 'my_custom_id' should be successfully deleted

  Scenario: Page template (' 123 ') deleted from a web service
    Given users want to delete uploaded page template with id ' 123 '
    When users delete information on the uploaded page template
    Then page template with id ' 123 ' should be successfully deleted

  Scenario: Page template ('<>') deleted from a web service
    Given users want to delete uploaded page template with id '<>'
    When users delete information on the uploaded page template
    Then page template with id '<>' should be successfully deleted

  Scenario: Page template ('-0') deleted from a web service
    Given users want to delete uploaded page template with id '-0'
    When users delete information on the uploaded page template
    Then page template with id '-0' should be successfully deleted

  Scenario: Page template ('$*') deleted from a web service
    Given users want to delete uploaded page template with id '$*'
    When users delete information on the uploaded page template
    Then page template with id '$*' should be successfully deleted
