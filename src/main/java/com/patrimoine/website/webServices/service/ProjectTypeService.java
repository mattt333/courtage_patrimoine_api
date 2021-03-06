package com.patrimoine.website.webServices.service;

import com.patrimoine.website.webServices.entity.Project;
import com.patrimoine.website.webServices.entity.ProjectType;
import com.patrimoine.website.webServices.repository.ProjectTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectTypeService {

    @Autowired
    ProjectTypeRepository projectTypeRepository;

    //GetAll
    public List<ProjectType> getAllProjectType(){
        return projectTypeRepository.findAll();
    }

    //GetById
    public ProjectType getProjectTypeById(Long id){
        Optional<ProjectType> optionalProjectType = projectTypeRepository.findById(id);
            if(optionalProjectType.isPresent()) {
            return optionalProjectType.get();
            }
        return null;
    }

    //Create
    public ProjectType createProjectType(ProjectType projectType){
        return projectTypeRepository.save(projectType);
    }

    //Update
    public ProjectType updateProjectType(ProjectType projectType, Long id){
        if(id == projectType.getId()){
            ProjectType projectTypeUpdated = projectTypeRepository.findById(id).get();
            projectTypeUpdated.setName(projectType.getName());
            projectTypeUpdated.setProjectStatuses(projectType.getProjectStatuses());
            return projectTypeRepository.save(projectTypeUpdated);
        }
        throw new ResponseStatusException(
                HttpStatus.PRECONDITION_FAILED);
    }

    //Delete
    public void deleteProjectType(Long id){
        projectTypeRepository.deleteById(id);
    }

}
