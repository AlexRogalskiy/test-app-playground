@rest-api-issues
Feature: REST API common issues with page template upload/fetch/delete/install operations
  Users should be able to send POST, GET, DELETE requests to a web service to upload/fetch/delete/install page templates

  Scenario: Page template with empty id uploaded to a web service (that cannot be deleted)
    Given users want to upload page template
    And users provide page template file 'src/test/resources/templates/request/template_1.yaml'
    And users provide page template id ''
    When users upload page template
    Then page template with id '' should be successfully uploaded

  Scenario: Page template with invalid header uploaded to a web service
    Given users want to upload page template
    And users provide page template file 'src/test/resources/templates/request/template_1.yaml'
    And users provide request header 'content-type':'multipart/form-data'
    When users upload page template
    Then upload operation should fail with status (400) and message 'No file part in the request'
