package atipera.task.githubrepositorieslisting.dto;

public class UserRepoDto {
    private String name;
    private Owner owner;
    private boolean fork;

    //Getters
    public String getName() {
        return name;
    }

    public Owner getOwner() {
        return owner;
    }

    public boolean isFork() {
        return fork;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    //Structure of API response
    public static class Owner{
        private String login;

        //Getters
        public String getLogin() {
            return login;
        }

        //Setters
        public void setLogin(String login) {
            this.login = login;
        }
    }
}
