package com.zenloww.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer projectid;
    private String projectname;
    private String projectdescription;

    public Project(String projectname, String projectdescription) {
        this.projectname=projectname;
        this.projectdescription=projectdescription;
    }

}
