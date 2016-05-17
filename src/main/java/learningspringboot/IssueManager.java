package learningspringboot;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.github.api.GitHubIssue;
import org.springframework.social.github.api.impl.GitHubTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tm1c14 on 17/05/2016.
 */
@Service
public class IssueManager implements InitializingBean {

    @Value("${github.token}")
    private String gitHubToken;
    @Value("${org}")
    private String org;

    private GitHubTemplate gitHubTemplate;

    @Value("${repos}")
    private String [] repos ;

    public List<Issue> findOPenIssues() {
        List<Issue> openIssues = new ArrayList<Issue>();

        for(String repo: repos) {
            final List<GitHubIssue> issues = gitHubTemplate.repoOperations().getIssues(org, repo);

            for(GitHubIssue issue: issues) {
                if(issue.getState().equals("open")) {
                    openIssues.add(new Issue(repo, issue));
                }
            }
        }

        return openIssues;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        gitHubTemplate = new GitHubTemplate(gitHubToken);
    }
}
