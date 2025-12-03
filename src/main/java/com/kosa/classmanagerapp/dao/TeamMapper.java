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

//    void insertTeamMember(int teamId, String teamName, long userId, String userName, String userFullName);
//void insertTeamMember(
//        @Param("teamId") int teamId,
//        @Param("teamName") String teamName,
//        @Param("userId") long userId,
//        @Param("userName") String userName,
//        @Param("fullName") String fullName
//);

    // 팀 정보 업데이트
    int updateTeamMember(Team team);


}
