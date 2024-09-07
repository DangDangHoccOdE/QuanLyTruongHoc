package vn.springboot.QuanLyTruongHoc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.springboot.QuanLyTruongHoc.dao.ScoreSheetDao;
import vn.springboot.QuanLyTruongHoc.entity.ScoreSheet;
import vn.springboot.QuanLyTruongHoc.service.inter.IScoreSheetService;

@Service
public class ScoreSheetService implements IScoreSheetService {
    private final ScoreSheetDao scoreSheetDao;

    @Autowired
    public ScoreSheetService(ScoreSheetDao scoreSheetDao){
        this.scoreSheetDao =scoreSheetDao;
    }

    @Override
    public ScoreSheet findScoreSheetBySemesterYearAndClassId(String semesterYear, int classId) {
        return scoreSheetDao.findScoreSheetBySemesterYearAndClassroom_Id(semesterYear,classId);
    }

}
