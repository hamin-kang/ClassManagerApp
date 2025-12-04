package com.kosa.classmanagerapp.dao;

import com.kosa.classmanagerapp.model.Project;
import com.kosa.classmanagerapp.model.Team;
import com.kosa.classmanagerapp.model.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeamMapper {

    int save(Team team);

    int count();
    // 모든 팀 조회
    List<Team> findAll();


    // 팀 정보 업데이트
    int updateTeamMember(Team team);


    int insertTeamMember(Team team);

    int existsTeam(int idInt);
}
