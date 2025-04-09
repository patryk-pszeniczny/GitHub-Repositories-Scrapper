# GitHub Repositories Scrapper
A simple application for downloading someones GitHub repositories in a specified format.

**NOTE:** GitHub Repositories Scrapper is not an any official API.

## RestAPI (Params and description)

| Type | Param Required | Endpoint | Description |
| ---------- | -------- | -------- | ----------- |
| Repositories | Yes | /{username} | Get user github all public repositories (no forks) Example: `http://localhost:8080/api/v1/atiperatask/patrykpszeniczny` |

## Use Docker Image

[Get Docker Image](https://hub.docker.com/repository/docker/patrykpszeniczny/atipera-task/general) 
(Please always use specific tag version instead of latest version)

Command:
```docker container run --name atiperaghapi -p 8080:8080 patrykpszeniczny/atipera-task```

GitHub API path in app configuration to override in docker if needed: 
```github.api.url```




