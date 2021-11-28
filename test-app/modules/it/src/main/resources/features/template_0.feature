@rest-api0
Feature: Testing a REST API with page template-0
  Users should be able to send POST, GET, DELETE requests to a web service to upload/fetch/delete/install page templates

  Scenario: Page templates retrieved from a web service
    Given users want to fetch uploaded page templates
    When users request information on the uploaded page templates
    Then page templates data 'template_0_1.json' should be successfully returned

  Scenario: Page template (template_0) deleted from a web service
    Given users want to delete uploaded page template with id 'template_0'
    When users delete information on the uploaded page template
    Then delete operation should fail as page template with id 'template_0' should not be found

  Scenario: Page template (template_0) installed on a web service
    Given users want to install uploaded page template with id 'template_0'
    When users install uploaded page template
    Then install operation should fail with page template 'template_0' not found

  Scenario: Page template (template_0) uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'template_0.yaml'
    When users upload page template
    Then page template with id 'template_0' should be successfully uploaded

  Scenario: Page templates retrieved from a web service
    Given users want to fetch uploaded page templates
    When users request information on the uploaded page templates
    Then page templates data 'template_0_2.json' should be successfully returned

  Scenario: Page template (template_0) installed on a web service
    Given users want to install uploaded page template with id 'template_0'
    When users install uploaded page template
    Then page template with id 'template_0' should be successfully installed

  Scenario: Page template (template_0) rendered on a web service
    Given users want to view installed page template on a web page
    And users want to compare with file template 'template_0.yaml'
    When users navigate to web page
    Then page template should be correctly rendered

  Scenario: Page template (template_0) deleted from a web service
    Given users want to delete uploaded page template with id 'template_0'
    When users delete information on the uploaded page template
    Then page template with id 'template_0' should be successfully deleted

  Scenario: Page template (template_0) with id (alphabetic symbols) uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'template_0.yaml'
    And users provide page template id 'my_custom_id'
    When users upload page template
    Then page template with id 'my_custom_id' should be successfully uploaded

  Scenario: Page template (my_custom_id) installed on a web service
    Given users want to install uploaded page template with id 'my_custom_id'
    When users install uploaded page template
    Then page template with id 'my_custom_id' should be successfully installed

  Scenario: Page template ('my_custom_id') rendered on a web service
    Given users want to view installed page template on a web page
    And users want to compare with file template 'template_0.yaml'
    When users navigate to web page
    Then page template should be correctly rendered

  Scenario: Page template (template_0) with id (space symbols) uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'template_0.yaml'
    And users provide page template id ' 123 '
    When users upload page template
    Then page template with id ' 123 ' should be successfully uploaded

  Scenario: Page template (' 123 ') installed on a web service
    Given users want to install uploaded page template with id ' 123 '
    When users install uploaded page template
    Then page template with id ' 123 ' should be successfully installed

  Scenario: Page template (' 123 ') rendered on a web service
    Given users want to view installed page template on a web page
    And users want to compare with file template 'template_0.yaml'
    When users navigate to web page
    Then page template should be correctly rendered

  Scenario: Page template (template_0) with id (special symbols) uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'template_0.yaml'
    And users provide page template id '$*'
    When users upload page template
    Then page template with id '$*' should be successfully uploaded

  Scenario: Page template ('$*') installed on a web service
    Given users want to install uploaded page template with id '$*'
    When users install uploaded page template
    Then page template with id '$*' should be successfully installed

  Scenario: Page template ('$*') rendered on a web service
    Given users want to view installed page template on a web page
    And users want to compare with file template 'template_0.yaml'
    When users navigate to web page
    Then page template should be correctly rendered

  Scenario: Page template (template_0) with id (numeric symbols) uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'template_0.yaml'
    And users provide page template id '-0'
    When users upload page template
    Then page template with id '-0' should be successfully uploaded

  Scenario: Page template ('-0') installed on a web service
    Given users want to install uploaded page template with id '-0'
    When users install uploaded page template
    Then page template with id '-0' should be successfully installed

  Scenario: Page template ('-0') rendered on a web service
    Given users want to view installed page template on a web page
    And users want to compare with file template 'template_0.yaml'
    When users navigate to web page
    Then page template should be correctly rendered

  Scenario: Page template (template_0) with id (squared symbols) uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'template_0.yaml'
    And users provide page template id '<>'
    When users upload page template
    Then page template with id '<>' should be successfully uploaded

  Scenario: Page template ('<>') installed on a web service
    Given users want to install uploaded page template with id '<>'
    When users install uploaded page template
    Then page template with id '<>' should be successfully installed

  Scenario: Page template ('<>') rendered on a web service
    Given users want to view installed page template on a web page
    And users want to compare with file template 'template_0.yaml'
    When users navigate to web page
    Then page template should be correctly rendered

  Scenario: Page templates retrieved from a web service
    Given users want to fetch uploaded page templates
    When users request information on the uploaded page templates
    Then page templates data 'template_0_3.json' should be successfully returned

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
