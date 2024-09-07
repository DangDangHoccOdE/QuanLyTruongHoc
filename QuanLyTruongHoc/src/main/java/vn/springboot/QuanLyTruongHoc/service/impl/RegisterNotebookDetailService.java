package vn.springboot.QuanLyTruongHoc.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.springboot.QuanLyTruongHoc.dao.RegisterNotebookDetailDao;
import vn.springboot.QuanLyTruongHoc.dto.RegisterNotebookDetailDto;
import vn.springboot.QuanLyTruongHoc.entity.Classroom;
import vn.springboot.QuanLyTruongHoc.entity.RegisterNotebook;
import vn.springboot.QuanLyTruongHoc.entity.RegisterNotebookDetail;
import vn.springboot.QuanLyTruongHoc.entity.Teacher;
import vn.springboot.QuanLyTruongHoc.service.inter.IClassroomService;
import vn.springboot.QuanLyTruongHoc.service.inter.IRegisterNotebookDetailService;
import vn.springboot.QuanLyTruongHoc.service.inter.IRegisterNotebookService;
import vn.springboot.QuanLyTruongHoc.utils.ConvertString;
import vn.springboot.QuanLyTruongHoc.utils.SecurityUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterNotebookDetailService implements IRegisterNotebookDetailService {
    private final RegisterNotebookDetailDao registerNotebookDetailDao;
    private final IRegisterNotebookService iRegisterNotebookService;
    private final IClassroomService iClassroomService;
    private final ConvertString convertString;
    private final SecurityUtils securityUtils;

    @Autowired
    public RegisterNotebookDetailService(RegisterNotebookDetailDao registerNotebookDetailDao, IRegisterNotebookService iRegisterNotebookService, IClassroomService iClassroomService, ConvertString convertString, SecurityUtils securityUtils) {
        this.registerNotebookDetailDao = registerNotebookDetailDao;
        this.iRegisterNotebookService = iRegisterNotebookService;
        this.iClassroomService = iClassroomService;
        this.convertString = convertString;
        this.securityUtils = securityUtils;
    }

    @Override
    public List<RegisterNotebookDetail> getRegisterNotebookDetailByClassId(int id) {
        return registerNotebookDetailDao.getRegisterNoteDetailByClassroomId(id);
    }

    @Override
    @Transactional
    public void saveRegisterNotebookDetail(RegisterNotebookDetail registerNotebookDetail) {
        registerNotebookDetailDao.save(registerNotebookDetail);
    }


    @Override
    public RegisterNotebookDetail findRegisterNotebookDetailById(int id) {
        return registerNotebookDetailDao.getReferenceById(id);
    }

    @Override
    @Transactional
    public void delete(RegisterNotebookDetail registerNotebookDetail) {
        registerNotebookDetailDao.delete(registerNotebookDetail);
    }

    @Override
    public RegisterNotebookDetailDto getRegisterNotebookDetailDto(RegisterNotebookDetail registerNotebookDetail) {
        RegisterNotebookDetailDto registerNotebookDetailDto = new RegisterNotebookDetailDto();
        registerNotebookDetailDto.setTeacherId(registerNotebookDetail.getTeacher().getTeacherId());
        registerNotebookDetailDto.setContent(registerNotebookDetail.getContent());
        registerNotebookDetailDto.setSemester(registerNotebookDetail.getSemester());
        registerNotebookDetailDto.setTimeTeaching(registerNotebookDetail.getTimeTeaching().toString());
        registerNotebookDetailDto.setLesson(registerNotebookDetail.getLesson());
        return registerNotebookDetailDto;
    }

    @Override
    @Transactional
    public void saveRegisterNotebookDetail(RegisterNotebookDetailDto registerNotebookDetailDto, RegisterNotebookDetail registerNotebookDetail, int classId, Teacher teacher) {
        RegisterNotebook registerNotebook = iRegisterNotebookService.getRegisterNotebookByClassId(classId);

        registerNotebookDetail.setClassroom(iClassroomService.findClassroomById(classId));
        registerNotebookDetail.setTeacher(teacher);
        registerNotebookDetail.setContent(registerNotebookDetailDto.getContent());
        registerNotebookDetail.setSemester(registerNotebookDetailDto.getSemester());
        registerNotebookDetail.setTimeTeaching(convertString.convertStringToDate(registerNotebookDetailDto.getTimeTeaching()));
        registerNotebookDetail.setRegisterNotebook(registerNotebook);
        registerNotebookDetail.setLesson(registerNotebookDetailDto.getLesson());

        saveRegisterNotebookDetail(registerNotebookDetail);
    }

    @Override
    public List<RegisterNotebookDetail> findRegisterNotebookDetailByTeacher(Teacher teacher, Classroom classroom) {
        List<RegisterNotebookDetail> registerNotebookDetails = new ArrayList<>();
        if(securityUtils.hasPrincipal() || teacher == classroom.getTeacher()){
            registerNotebookDetails = getRegisterNotebookDetailByClassId(classroom.getId());
        }else{
            for (RegisterNotebookDetail rnd :classroom.getRegisterNotebookDetails()) {
                if (rnd.getTeacher() == teacher) {
                    registerNotebookDetails.add(rnd);
                }
            }
        }
        return registerNotebookDetails;
    }
}
