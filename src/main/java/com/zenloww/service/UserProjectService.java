package com.zenloww.service;

import com.zenloww.entity.UserProjectMembership;
import com.zenloww.repository.ProjectRepository;
import com.zenloww.repository.UserProjectRepository;
import com.zenloww.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
public class UserProjectService {
    private UserProjectRepository userProjectRepository;
    private UserRepository userRepository;
    private ProjectRepository projectRepository;

    public UserProjectMembership addUserToProject(Integer projectid, Integer userid) {
        if(projectRepository.existsById(projectid) && (userRepository.existsById(userid))){
            if(!userProjectRepository.existsByProjectidAndUserid(projectid, userid)){
                UserProjectMembership savedMembership = userProjectRepository.save(new UserProjectMembership(projectid, userid));
                return savedMembership;
            }
        }
        else {
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "ID already present!");
        }
        return null;
    }
}
