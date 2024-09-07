package vn.springboot.QuanLyTruongHoc.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.springboot.QuanLyTruongHoc.dao.ParentDao;
import vn.springboot.QuanLyTruongHoc.dto.ParentDto;
import vn.springboot.QuanLyTruongHoc.entity.Parent;
import vn.springboot.QuanLyTruongHoc.entity.Student;
import vn.springboot.QuanLyTruongHoc.service.inter.IParentService;

@Service
public class ParentService implements IParentService {
    private final ParentDao parentDao;

    @Autowired
    public ParentService(ParentDao parentDao){
        this.parentDao = parentDao;
    }

    @Override
    @Transactional
    public void saveParent(Parent parent) {
        parentDao.save(parent);
    }

    @Override
    public Parent findParentByStudentId(String studentId) {
        return parentDao.findParentByStudent_StudentId(studentId);
    }

    @Override
    public ParentDto getParentDto(Student student) {
        Parent parent = student.getParent();
        ParentDto parentDto = new ParentDto();
        parentDto.setParentName(parent.getParentName());
        parentDto.setParentPhone(parent.getPhone());
        parentDto.setParentDate(parent.getDateOfBirth().toString());
        parentDto.setParentAddress(parent.getAddress());
        return parentDto;
    }
}
