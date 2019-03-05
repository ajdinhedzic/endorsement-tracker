package com.hedzic.ajdin.endorsementtracker.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Pilot {
    @Id
    @GeneratedValue
    private Long id;
    private String flightTrainingNumber;
    @OneToMany
    private List<Instructor> instructors;
    @OneToOne
    private UserAccount userAccount;

    public Pilot() {
    }

    public Pilot(UserAccount userAccount){
        this.userAccount = userAccount;
    }

    public Long getId() {
        return id;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public String getFlightTrainingNumber() {
        return flightTrainingNumber;
    }

    public void setFlightTrainingNumber(String flightTrainingNumber) {
        this.flightTrainingNumber = flightTrainingNumber;
    }

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }
}
