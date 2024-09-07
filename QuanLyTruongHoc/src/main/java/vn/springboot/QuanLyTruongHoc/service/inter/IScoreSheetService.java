package vn.springboot.QuanLyTruongHoc.service.inter;

import vn.springboot.QuanLyTruongHoc.entity.ScoreSheet;

public interface IScoreSheetService {
    ScoreSheet findScoreSheetBySemesterYearAndClassId(String semesterYear,int classId);
}
