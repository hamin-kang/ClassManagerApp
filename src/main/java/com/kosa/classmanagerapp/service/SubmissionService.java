package com.kosa.classmanagerapp.service;

import com.kosa.classmanagerapp.model.Submission;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SubmissionService {
    List<Submission> submissions = new ArrayList<>();
    public SubmissionService(){
        for(int i = 0 ; i < 10 ; i++){
            Submission submission = new Submission.Builder()
                    .assignmentId(i + 1)
                    .submitterUserId(100 + i)
                    .content("과제 내용 " + (i + 1))
                    .submittedAd(LocalDate.now().minusDays(i))
                    .build();
            submissions.add(submission);
        }
    }

}
