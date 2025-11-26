package com.kosa.classmanagerapp.service;

import com.kosa.classmanagerapp.model.Submission;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//제출 관리
public class SubmissionService {
    List<Submission> submissions = new ArrayList<>();
    public SubmissionService(){
        for(int i = 0 ; i < 10 ; i++){
            boolean isSubmitted = (i % 2 == 0);

            Submission submission = new Submission.Builder()
                    .assignmentId(i + 1)
                    .submitterUserId(1)
                    .content("과제 내용 " + (i + 1))
                    .submittedAd(LocalDate.now().minusDays(i))
                    .isSubmitted(isSubmitted)
                    .build();
            submissions.add(submission);
        }
    }
    public List<Submission> findByUserId(long user_id){
        //db 접속해서 해당 id가 제출한 과제 가져옴
        return submissions;
    }



}
