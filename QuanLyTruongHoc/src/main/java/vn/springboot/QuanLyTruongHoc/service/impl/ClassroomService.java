package vn.springboot.QuanLyTruongHoc.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.springboot.QuanLyTruongHoc.dao.ClassroomDao;
import vn.springboot.QuanLyTruongHoc.dto.ClassroomDto;
import vn.springboot.QuanLyTruongHoc.entity.Classroom;
import vn.springboot.QuanLyTruongHoc.entity.Roles;
import vn.springboot.QuanLyTruongHoc.entity.Subject;
import vn.springboot.QuanLyTruongHoc.entity.Teacher;
import vn.springboot.QuanLyTruongHoc.service.inter.IClassroomService;
import vn.springboot.QuanLyTruongHoc.service.inter.ISubjectService;
import vn.springboot.QuanLyTruongHoc.service.inter.ITeacherService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassroomService implements IClassroomService {
    private final ClassroomDao classroomDao;
    private final ISubjectService iSubjectService;
    private final ITeacherService iTeacherService;

    @Autowired
    public ClassroomService(ClassroomDao classroomDao, ISubjectService iSubjectService, ITeacherService iTeacherService){
        this.classroomDao = classroomDao;
        this.iSubjectService = iSubjectService;
        this.iTeacherService = iTeacherService;
    }

    @Override
    public List<Classroom> showAllClassroom(){
        return classroomDao.findAll(Sort.by(Sort.Direction.DESC,"className"));
    }

    @Override
    public Classroom findClassroomByName(String name) {
        return classroomDao.findClassroomByClassName(name);
    }

    @Override
    @Transactional
    public void saveClassroom(Classroom classroom) {
        classroomDao.save(classroom);
    }

    @Override
    public Classroom findClassroomById(int id) {
        return classroomDao.getReferenceById(id);
    }

    @Override
    @Transactional
    public void deleteClassroom(Classroom classroom) {
        classroomDao.delete(classroom);
    }

    @Override
    public List<Teacher> getAllHomeRoomTeacher() {
        List<Teacher> teacherList = iTeacherService.findAll();
        List<Teacher> teachers = new ArrayList<>();
        for(Teacher teacher : teacherList){
            List<Roles> roles = teacher.getRoles();

            for (Roles role : roles) {
                if(role.getRoleName().equals("ROLE_HOMEROOMTEACHER")){
                    teachers.add(teacher);
                    break;
                }
            }
        }
        return teachers;
    }

    @Override
    public ClassroomDto findClassroomDto(Classroom classroom) {
        ClassroomDto classroomDto = new ClassroomDto();
        classroomDto.setClassName(classroom.getClassName());
        classroomDto.setTeacherId(classroom.getTeacher().getTeacherId());
        classroomDto.setClassBlock(classroom.getClassBlock());
        classroomDto.setSchoolYear(classroom.getSchoolYear());

        List<Integer> subjectId = new ArrayList<>();
        List<Subject> subjectList = classroom.getSubjects();

        for (Subject subject : subjectList) {
            subjectId.add(subject.getSubId());
        }

        classroomDto.setSubjectsId(subjectId);
        return classroomDto;
    }

    @Override
    public Classroom getClassroom(ClassroomDto classroomDto, Classroom classroom) {
        List<Integer> subjectsId = classroomDto.getSubjectsId();
        List<Subject> subjectList = new ArrayList<>();

        for (Integer s : subjectsId) {
            subjectList.add(iSubjectService.findSubjectById(s));
        }

        classroom.setClassName(classroomDto.getClassName());
        classroom.setClassBlock(classroomDto.getClassBlock());
        classroom.setSchoolYear(classroomDto.getSchoolYear());
        classroom.setTeacher(iTeacherService.findTeacherById(classroomDto.getTeacherId()));
        classroom.setSubjects(subjectList);

        return classroom;
    }
}
