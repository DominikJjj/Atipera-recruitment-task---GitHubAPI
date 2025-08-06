package atipera.task.githubrepositorieslisting.controller;

import atipera.task.githubrepositorieslisting.dto.UserRepoFullInfoDto;
import atipera.task.githubrepositorieslisting.service.GitHubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class GitHubController {
    private final GitHubService gitHubService;

    public GitHubController(GitHubService gitHubService){
        this.gitHubService = gitHubService;
    }

    @GetMapping("{user}/repositories")
    public ResponseEntity<?> getUserRepos(@PathVariable("user") String user){
        List<UserRepoFullInfoDto> result = gitHubService.getUserRepos(user);

        if (!result.isEmpty()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok("User: " + user + " exists but doesn't have any repositories or all repositories are forks.");
    }
}
