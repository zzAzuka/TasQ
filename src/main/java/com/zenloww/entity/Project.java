package com.zenloww.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "project")
    private List<Task> task = new ArrayList<>();

    public Project(String projectname, String projectdescription) {
        this.projectname=projectname;
        this.projectdescription=projectdescription;
    }

}
