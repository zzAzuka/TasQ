package com.zenloww.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "userprojectmembership")
public class UserProjectMembership {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer userprojectid;
    private Integer userid;
    private Integer projectid;

    public UserProjectMembership(Integer projectid, Integer userid) {
        this.projectid=projectid;
        this.userid=userid;
    }
}
