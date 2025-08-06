# Recruitment task - GitHub API
This API lists all GitHub Repositories which aren't forks, created by specified user.
Response contains: repositoryName, ownerLogin and all branchesNames with last commit sha for each branch.
It also verifies if specified user exists. If not client recieves 404 (Not Found) with proper error message.

There is also integration test for happy path created.

## Contents
- [Installation](#installation)
- [Endpoint](#endpoint)
- [Technologies](#technologies)

## Installation
```command line
git clone https://github.com/DominikJjj/Atipera-recruitment-task---GitHubAPI.git
```
Compile using IDE.

## Endpoint
There is only one endpoint:
http://localhost:PORT/users/{specifiedUser}/repositories
specifiedUser -> who`s GitHub Repositories list you want

SwaggerUI or GUI wasn't implemented so you need to use PostMan for testing.

## Technologies
- Java 21
- Spring 3.5.4







  
