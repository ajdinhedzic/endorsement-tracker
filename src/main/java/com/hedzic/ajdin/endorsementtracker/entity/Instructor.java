package com.hedzic.ajdin.endorsementtracker.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Instructor extends Pilot {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    private List<Pilot> students;

    public Instructor(){
    }
}
