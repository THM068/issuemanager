package learningspringboot;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tm1c14 on 23/05/2016.
 */
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<Teammate> members;

    public Team() {
        setMembers(new ArrayList<>());
    }

    public Team(String name) {
        this();
        this.setName(name);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Teammate> getMembers() {
        return members;
    }

    public void setMembers(List<Teammate> members) {
        this.members = members;
    }
}
