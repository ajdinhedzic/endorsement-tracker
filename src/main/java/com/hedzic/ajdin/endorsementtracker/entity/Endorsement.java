package com.hedzic.ajdin.endorsementtracker.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Endorsement {

    @Id
    @GeneratedValue
    private Long id;

    private Pilot instructor;
    private Pilot student;

}
