@rest-api12
Feature: Testing a REST API with page template-12 (multiple templates)
  Users should be able to send POST, GET, DELETE requests to a web service to upload/fetch/delete/install page templates

  Scenario: Page template (template_12_1) uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'template_12_1.yaml'
    When users upload page template
    Then page template with id 'template_12_1' should be successfully uploaded

  Scenario: Page template (template_12_2) uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'template_12_2.yaml'
    When users upload page template
    Then page template with id 'template_12_2' should be successfully uploaded

  Scenario: Page template (template_12_3) uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'template_12_3.yaml'
    When users upload page template
    Then page template with id 'template_12_3' should be successfully uploaded

  Scenario: Page template (template_12_4) uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'template_12_4.yaml'
    When users upload page template
    Then page template with id 'template_12_4' should be successfully uploaded

  Scenario: Page template (template_12_5) uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'template_12_5.yaml'
    When users upload page template
    Then page template with id 'template_12_5' should be successfully uploaded

  Scenario: Page template (template_12_6) uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'template_12_6.yaml'
    When users upload page template
    Then page template with id 'template_12_6' should be successfully uploaded

  Scenario: Page templates retrieved from a web service
    Given users want to fetch uploaded page templates
    When users request information on the uploaded page templates
    Then page templates data 'template_12.json' should be successfully returned

  Scenario: Page template (template_12_1) installed on a web service
    Given users want to install uploaded page template with id 'template_12_1'
    When users install uploaded page template
    Then install operation should fail with status (400) and message 'Links without labels are not allowed. Error occurred in {\'id\': \'element-1\', \'link\': \'http://element-1.ru\'}'

  Scenario: Page template (template_12_2) installed on a web service
    Given users want to install uploaded page template with id 'template_12_2'
    When users install uploaded page template
    Then install operation should fail with status (400) and message 'No "id" field in {\'idd\': \'element-a\', \'label\': \'Element a\', \'link\': \'http://element-a.ru\'}'

  Scenario: Page template (template_12_3) installed on a web service
    Given users want to install uploaded page template with id 'template_12_3'
    When users install uploaded page template
    Then page template with id 'template_12_3' should be successfully installed

  Scenario: Page template (template_12_3) rendered on a web service
    Given users want to view installed page template on a web page
    And users want to compare with file template 'template_12_3.yaml'
    When users navigate to web page
    Then page template should be correctly rendered

  Scenario: Page template (template_12_4) installed on a web service
    Given users want to install uploaded page template with id 'template_12_4'
    When users install uploaded page template
    Then install operation should fail with status (500)

  Scenario: Page template (template_12_5) installed on a web service
    Given users want to install uploaded page template with id 'template_12_5'
    When users install uploaded page template
    Then install operation should fail with status (400) and message 'Invalid template format!'

  Scenario: Page template (template_12_6) installed on a web service
    Given users want to install uploaded page template with id 'template_12_6'
    When users install uploaded page template
    Then install operation should fail with status (400) and message 'Links without labels are not allowed. Error occurred in {\'id\': \'element-a\', \'labels\': \'Element a\', \'link\': \'http://element-a.ru\'}'

  Scenario: Page template (template_12_1) deleted from a web service
    Given users want to delete uploaded page template with id 'template_12_1'
    When users delete information on the uploaded page template
    Then page template with id 'template_12_1' should be successfully deleted

  Scenario: Page template (template_12_2) deleted from a web service
    Given users want to delete uploaded page template with id 'template_12_2'
    When users delete information on the uploaded page template
    Then page template with id 'template_12_2' should be successfully deleted

  Scenario: Page template (template_12_3) deleted from a web service
    Given users want to delete uploaded page template with id 'template_12_3'
    When users delete information on the uploaded page template
    Then page template with id 'template_12_3' should be successfully deleted

  Scenario: Page template (template_12_4) deleted from a web service
    Given users want to delete uploaded page template with id 'template_12_4'
    When users delete information on the uploaded page template
    Then page template with id 'template_12_4' should be successfully deleted

  Scenario: Page template (template_12_5) deleted from a web service
    Given users want to delete uploaded page template with id 'template_12_5'
    When users delete information on the uploaded page template
    Then page template with id 'template_12_5' should be successfully deleted

  Scenario: Page template (template_12_6) deleted from a web service
    Given users want to delete uploaded page template with id 'template_12_6'
    When users delete information on the uploaded page template
    Then page template with id 'template_12_6' should be successfully deleted
