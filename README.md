# Search through your repo Application

## Description
The application allows you to search in your repositories
The search takes place across all repositories within a certain organization. In each of the repositories, the specified file (which is searched) and its contents are analyzed. If the contents of the file contain the desired substring, then this repository will be displayed in the found

![image_2024-05-04_04-35-48](https://github.com/Fant1k34/JB-YouTrack/assets/45245696/87aad31a-03b7-43f9-aad0-513cd834f1d4)

![image_2024-05-04_04-35-06](https://github.com/Fant1k34/JB-YouTrack/assets/45245696/17471542-5628-45d9-a35c-9892d44aa794)

![image_2024-05-04_04-34-38](https://github.com/Fant1k34/JB-YouTrack/assets/45245696/acad2d4b-ca84-4596-863c-585939537973)

![image_2024-05-04_04-37-19](https://github.com/Fant1k34/JB-YouTrack/assets/45245696/f75ed220-d895-477d-a39a-386d3cfaaa08)


### Arguments
1. Organization name
2. Github API key (different users have different access rights to projects, so specifying this field will allow you to view all repositories on your behalf. Please also make sure that the API key is generated correctly and has access to view the list of all repositories in the organization, as well as the repositories themselves and the files inside them)
3. Search file (the file or the full path to the file in the project that is being searched. Please note that if this file does not exist in the project, then such a project will not be included in the list of filtered repositories. It is also worth noting that the file search README.md and readme.me different)
4. Keyword (the substring is being analyzed)

### Default arguments
1. Organization name: default is "" (empty), required field
2. Github API key: default is "" (empty), required field
3. Search file: default is "README.md", required field
4. Keyword: default is "Hello", required field

### Possible errors
- Organization within this access token does not exist: the error shows either an empty field for a keyword, a file to search for, or the absence of an organization (including the lack of access to the organization under the current API key)
- Keyword could not be empty: the error shows an empty field for searching by substring
- File must be defined: the error shows an empty field for the file to be searched

## Architecture
The application consists of two parts: frontend, which generates js code, and server, which processes user requests, returns compiled statics and processes client requests for filtering repositories

### Frontend
- The frontend part is written in React with css-modules
- Webpack collects the application into one bundle and puts the bundle.js on the following path: src/main/resources/templates/bundle.js
- To build a frontend, you need a node.js (environment for executing JS code)
- Prettier is used as a linter
- Supported versions: Node.js 18.12.0+

### Server
- The application is a web application written in the Spring Framework (WebFlux)
- The main element is the server, which performs static output (html + js), as well as processing API requests
- The server processes the following urls:
  - GET /  returns html (src/main/resources/templates/index.html)
  - GET /bundle.js  returns js (src/main/resources/templates/bundle.js)
  - POST /filter-repos-api  to get a list of repositories in the organization and filter
    - Accepts arguments in the request body in JSON format
    - token (GITHUB API TOKEN/KEY)
    - keyword
    - link (organization name)
    - searchFile
- The main language of the server is Kotlin
- Supported versions: JVM 17+

### How to run it
- Install JVM 17+
- Install Gradle and it's dependencies
- Install Node.js 18.12.0+
- Install yarn globally
- yarn install && yarn start
  - yarn start is a script that firstly compile front-end code to bundle.js and then start server (Tomcat) with gradle
