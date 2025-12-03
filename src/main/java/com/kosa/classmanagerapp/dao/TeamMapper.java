package com.kosa.classmanagerapp.dao;

import com.kosa.classmanagerapp.model.Project;
import com.kosa.classmanagerapp.model.Team;

import java.util.List;

public interface TeamMapper {
    int save(Team team);

    int count();
    List<Team> findAll();
}
