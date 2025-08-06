package atipera.task.githubrepositorieslisting.dto;

public class UserBranchInfoDto {
    private String name;
    private Commit commit;

    //Getters
    public String getName() {
        return name;
    }

    public Commit getCommit() {
        return commit;
    }


    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    //Structure of API response
    public static class Commit{
        private String sha;

        //Getters
        public String getSha() {
            return sha;
        }

        //Setters
        public void setSha(String sha) {
            this.sha = sha;
        }
    }
}


