package atipera.task.githubrepositorieslisting.dto;

public class RepoBranchInfoDto {
    private String branchName;
    private String sha;

    public RepoBranchInfoDto(String branchName, String sha){
        this.branchName = branchName;
        this.sha = sha;
    }

    //Getters
    public String getBranchName() {
        return branchName;
    }

    public String getSha() {
        return sha;
    }

    //Setters
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }
}
