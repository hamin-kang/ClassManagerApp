package com.kosa.classmanagerapp.service;

import com.kosa.classmanagerapp.dao.ProjectMapper;
import com.kosa.classmanagerapp.dao.TeamMapper;
import com.kosa.classmanagerapp.dao.UserMapper;
import com.kosa.classmanagerapp.model.Team;
import com.kosa.classmanagerapp.global.initData.InitDataMemory;
import com.kosa.classmanagerapp.model.entity.User;
import com.kosa.classmanagerapp.util.SqlSessionManager;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class TeamService {

    private final List<Team> teams = new ArrayList<>();

    public TeamService() {
    }

    public List<Team> findAll() {
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession()) {
            TeamMapper mapper = session.getMapper(TeamMapper.class);
            return mapper.findAll(); // DB에서 가져옴
        }
    }

    public List<Team> findByProjectId(long projectId) {
        return teams.stream()
                .filter(t -> t.getProjectId() == projectId)
                .toList();
    }

    public int save(Team team) {
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true)) {
            TeamMapper mapper = session.getMapper(TeamMapper.class);
            int result = mapper.save(team);
            return result;
        }
    }

    public boolean isEmpty() {
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession()) {
            TeamMapper mapper = session.getMapper(TeamMapper.class);
            return mapper.count() == 0;
        }
    }

    //  --- 팀 update---
    public int updateTeamMember(Team team) {
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession()) {
            TeamMapper mapper = session.getMapper(TeamMapper.class);
            int result = mapper.updateTeamMember(team);
            session.commit();
            return result;
        }


    }
}



