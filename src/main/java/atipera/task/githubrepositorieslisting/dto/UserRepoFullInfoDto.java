package atipera.task.githubrepositorieslisting.dto;

import java.util.ArrayList;
import java.util.List;

public class UserRepoFullInfoDto {
    private String repositoryName;
    private String ownerLogin;
    private List<RepoBranchInfoDto> repositoryBranches;

    public UserRepoFullInfoDto(String repositoryName, String ownerLogin, List<RepoBranchInfoDto> listBranch){
        this.repositoryName = repositoryName;
        this.ownerLogin = ownerLogin;
        this.repositoryBranches = listBranch;
    }

    //Getters
    public String getRepositoryName() {
        return repositoryName;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public List<RepoBranchInfoDto> getRepositoryBranches() {
        return repositoryBranches;
    }

    //Setters

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public void setRepositoryBranches(List<RepoBranchInfoDto> repositoryBranches) {
        this.repositoryBranches = repositoryBranches;
    }
}
