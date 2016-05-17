package learningspringboot;

/**
 * Created by thandomafela on 17/05/2016.
 */
public class Issue {

    private String repo;
    private GitHubIsssue gitHubIssue;

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public GitHubIsssue getGitHubIssue() {
        return gitHubIssue;
    }

    public void setGitHubIssue(GitHubIsssue gitHubIssue) {
        this.gitHubIssue = gitHubIssue;
    }
}
