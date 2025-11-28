package com.kosa.classmanagerapp.service;

import com.kosa.classmanagerapp.model.Team;
import com.kosa.classmanagerapp.global.initData;

import java.util.ArrayList;
import java.util.List;

public class TeamService {

    private final List<Team> teams = new ArrayList<>();

    public TeamService() {
        teams.addAll(initData.createDummyTeams());
    }

    public List<Team> findAll() {
        return teams;
    }

    public List<Team> findByProjectId(long projectId) {
        return teams.stream()
                .filter(t -> t.getProject_id() == projectId)
                .toList();
    }
}
