package learningspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * Created by tm1c14 on 17/05/2016.
 */
@Controller
public class IssueController {

    private IssueManager issueManager;

    @Autowired
    public IssueController(IssueManager issueManager) {
        this.issueManager = issueManager;
    }

    @RequestMapping(value = "/")
    public String index(Model model){
        model.addAttribute("issues", issueManager.findOPenIssues());
        return "index";
    }
}
