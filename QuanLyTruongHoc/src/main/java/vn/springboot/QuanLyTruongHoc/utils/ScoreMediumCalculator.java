package vn.springboot.QuanLyTruongHoc.utils;

import org.springframework.stereotype.Component;
import vn.springboot.QuanLyTruongHoc.entity.*;

import java.text.DecimalFormat;
import java.util.*;

@Component
public class ScoreMediumCalculator {
    public ScoreMediumCalculator() {
    }

    public float scoreMediumCalculator(List<ReportCardDetail> reportCardDetailList){
        float scoreMedium = 0;
        int count =0;

        for (ReportCardDetail reportCardDetail : reportCardDetailList){
            String testName = reportCardDetail.getTestName();
            if(testName.equals("Điểm ĐGTX")){
                scoreMedium+=reportCardDetail.getScore();
                count++;
            }else if(testName.equals("Điểm giữa kỳ")){
                scoreMedium+=reportCardDetail.getScore()*2;
            }else{
                scoreMedium+=reportCardDetail.getScore()*3;
            }
        }

        scoreMedium/=(count+5);
        DecimalFormat df = new DecimalFormat("#.##");
        return Float.parseFloat(df.format(scoreMedium));
    }

    private List<Float> socreList(ScoreSheet scoreSheet, List<Student> students){
        List<ReportCard> reportCards = scoreSheet.getReportCards();
        List<Float> mediumScoreOfClassroom = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#.##");

        for(Student student : students) {
            float scoreMedium = 0;
            int count=0;
            for (ReportCard reportCard : reportCards) {
                if(reportCard.getStudent()==student){
                    scoreMedium +=reportCard.getScore();
                    count++;
                }
            }
            scoreMedium=scoreMedium/count;
            mediumScoreOfClassroom.add(Float.parseFloat(df.format(scoreMedium)));
        }

        return mediumScoreOfClassroom;
    }

    public Map<Student,Float> scoreMediumCalculatorOfSemester(ScoreSheet scoreSheet,List<Student>students){
        Map<Student,Float> studentFloatMap = new HashMap<>();
        List<Float> scoreList = socreList(scoreSheet,students);
        for(int i=0 ; i< scoreList.size() ; i++){
            float averageScore = scoreList.get(i);
            studentFloatMap.put(students.get(i),averageScore);
        }
        return studentFloatMap;
    }

    public Float scoreMediumOfStudent(List<ReportCard> reportCards){
        int count = 0;
        float score =0 ;
        for(ReportCard reportCard : reportCards){
            score+=reportCard.getScore();
            count++;
        }

        score=score/count;
        DecimalFormat df = new DecimalFormat("#.##");
        return Float.parseFloat(df.format(score));

    }
}
