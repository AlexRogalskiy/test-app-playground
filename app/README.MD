# TEST APP

This is a test app.

The app contains bugs.
Some of them are meant to be there while some might be usual bugs that appear during development.

> **Your task is to write a set of automated tests for it using pytest framework.**

Let's see how many of them you'll be able to find with automated tests!

## App spec

Test app allows users to upload templates and then render a simple Web page using this template.
The rendered page will contain buttons with the links provided. 
The web page and API are available on *http://localhost:5000/* after the app is launched.

### Build

The app should be started in a docker using

    docker build --tag test_app:latest .
    
    docker run -p 5000:5000 -d test_app:latest

Template should be a list of the following blocks in [Yaml format](https://en.wikipedia.org/wiki/YAML):

     -  id: {Element id. Mandatory}
        label: {Button label. Mandatory}
        link: {Web link. Optional}
        depends: {Id of parrent element. Optional}
        
### Constraints

- Each element should have **id** and **label**
- **id** should be unique
- If a link is not set for the element corresponding button will be rendered as disabled
- Parent element should be present in the list of elements
- Buttons on the rendered page should be clickable except case with no link provided

## API spec

### Upload template

  Upload your template file. 
  
> If **tmpl_id** is not provided in the request body then **tmpl_id** will be generated based on the filename.

Name     | Value
---:     | :---- 
Endpoint | `/api/v1/templates`
Method   | `POST`


* **Body**

  Content-type: **Form-data**
    
   * **Required:**
 
   `file=[file]`
   
   * **Optional:**
   
   `data={"tmpl_id":"my_custom_id"}`
   
### List templates

  List all currently uploaded templates

Name     | Value
---:     | :---- 
Endpoint | `/api/v1/templates`
Method   | `GET`


### Delete template

  Delete previously loaded template file

Name     | Value
---:     | :---- 
Endpoint | `/api/v1/templates/{tmpl_id}`
Method   | `DELETE`
  
*  **URL Params**

   **Required:**
 
   `tmpl_id=[string]`
   
#### Install template

  Install previously loaded template file

Name     | Value
---:     | :---- 
Endpoint | `/api/v1/templates/{tmpl_id}/install`
Method   | `POST`
  
*  **URL Params**

   **Required:**
 
   `tmpl_id=[string]`
