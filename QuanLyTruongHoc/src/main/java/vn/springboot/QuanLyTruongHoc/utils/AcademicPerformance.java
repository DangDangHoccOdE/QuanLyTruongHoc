package vn.springboot.QuanLyTruongHoc.utils;

import org.springframework.stereotype.Component;

@Component
public class AcademicPerformance {
    public AcademicPerformance() {
    }

    public String academicPerformance(float scoreMedium) {
        return scoreMedium >= 8 ? "Giỏi" :
                scoreMedium >= 6.5 ? "Khá" :
                        scoreMedium >= 5 ? "Trung Bình" :
                                scoreMedium >= 3.5 ? "Kém" :
                                        "Yếu";
    }

}
