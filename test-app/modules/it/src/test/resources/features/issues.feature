@rest-api-issues
Feature: REST API common issues with page template upload/fetch/delete/install operations
  Users should be able to send POST, GET, DELETE requests to a web service to upload/fetch/delete/install page templates

#  Scenario: Page template with empty id uploaded to a web service
#    Given users want to upload page template 'src/test/resources/templates/request/template_1.yaml'
#    When users upload page template with id ''
#    Then upload operation should succeed with status (201) for page template ''

  Scenario: Page template with empty id uploaded to a web service
    Given users want to upload page template 'src/test/resources/templates/request/template_1.yaml'
    When users upload page template with header 'content-type':'multipart/form-data'
    Then upload operation should fail with status (400) and message 'No file part in the request'
