package com.kosa.classmanagerapp.global;

import com.kosa.classmanagerapp.service.*;
import com.kosa.classmanagerapp.service.auth.UserService;

public class AppContext {
    public static final ProjectService PROJECT_SERVICE = new ProjectService();
    public static final TeamService TEAM_SERVICE = new TeamService();
    public static final UserService USER_SERVICE = new UserService();
    public static final AssignmentService ASSIGNMENT_SERVICE = new AssignmentService();
    public static final SubmissionService SUBMISSION_SERVICE = new SubmissionService();
}
