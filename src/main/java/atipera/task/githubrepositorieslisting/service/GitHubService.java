package atipera.task.githubrepositorieslisting.service;

import atipera.task.githubrepositorieslisting.dto.RepoBranchInfoDto;
import atipera.task.githubrepositorieslisting.dto.UserBranchInfoDto;
import atipera.task.githubrepositorieslisting.dto.UserRepoDto;
import atipera.task.githubrepositorieslisting.dto.UserRepoFullInfoDto;
import atipera.task.githubrepositorieslisting.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GitHubService {
    private static final String baseLinkAPI = "https://api.github.com";
    private final RestTemplate restTemplate;

    public GitHubService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public List<UserRepoFullInfoDto> getUserRepos(String user){

        if (!isUserExists(user)){
            throw new UserNotFoundException("User with login: " + user + " not found.");
        }

        UserRepoDto[] info = restTemplate.getForObject(
                baseLinkAPI + "/users/{user}/repos",
                UserRepoDto[].class,
                user
        );

        List<UserRepoFullInfoDto> resultList = new ArrayList<>();

        for (UserRepoDto temp : info){
            if (!temp.isFork()){
                resultList.add(new UserRepoFullInfoDto(temp.getName(), temp.getOwner().getLogin(), getBranchesInfo(temp.getOwner().getLogin(), temp.getName())));
            }
        }

        return resultList;
    }

    public List<RepoBranchInfoDto> getBranchesInfo(String user, String repo){
        UserBranchInfoDto[] info = restTemplate.getForObject(
                baseLinkAPI + "/repos/{user}/{repo}/branches",
                UserBranchInfoDto[].class,
                user, repo
        );

        List<RepoBranchInfoDto> resultList = new ArrayList<>();

        for (UserBranchInfoDto temp : info) {
            resultList.add(new RepoBranchInfoDto(temp.getName(), temp.getCommit().getSha()));
        }

        return resultList;
    }

    public boolean isUserExists(String user){
        String link = baseLinkAPI + "/users/{user}";
        try{
            restTemplate.headForHeaders(link, user);
            return true;
        }catch (HttpClientErrorException.NotFound exception){
            return false;
        }
    }
}
