package vn.springboot.QuanLyTruongHoc.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.springboot.QuanLyTruongHoc.dao.StudentDao;
import vn.springboot.QuanLyTruongHoc.dto.ParentDto;
import vn.springboot.QuanLyTruongHoc.dto.StudentDto;
import vn.springboot.QuanLyTruongHoc.entity.*;
import vn.springboot.QuanLyTruongHoc.service.inter.IAccountService;
import vn.springboot.QuanLyTruongHoc.service.inter.IParentService;
import vn.springboot.QuanLyTruongHoc.service.inter.IRolesService;
import vn.springboot.QuanLyTruongHoc.service.inter.IStudentService;
import vn.springboot.QuanLyTruongHoc.utils.ConvertString;

import java.util.List;

@Service
public class StudentService implements IStudentService {
    private final StudentDao studentDao;
    private final ConvertString convertString;
    private final IParentService iParentService;
    private final PasswordEncoder passwordEncoder;
    private final IAccountService iAccountService;
    private final IRolesService iRolesService;

    @Autowired
    public StudentService(StudentDao schoolDao, ConvertString convertString, IParentService iParentService, PasswordEncoder passwordEncoder, IAccountService iAccountService, IRolesService iRolesService){
        this.studentDao = schoolDao;
        this.convertString = convertString;
        this.iParentService = iParentService;
        this.passwordEncoder = passwordEncoder;
        this.iAccountService = iAccountService;
        this.iRolesService = iRolesService;
    }

    @Override
    public List<Student> showAllStudent(){
        return studentDao.findAll();
    }

    @Override
    @Transactional
    public void saveStudent(Student student){
         studentDao.save(student);
    }

    @Override
    public Student findStudentById(String id) {
        return studentDao.findStudentByStudentId(id);
    }

    @Override
    @Transactional
    public void deleteStudent(Student student) {
        studentDao.delete(student);
    }

    @Override
    public Student getStudent(Student student,StudentDto studentDto, ParentDto parentDto, Classroom classroom) {
        student.setStudentId(studentDto.getStudentId());
        student.setStudentName(studentDto.getName());
        student.setGender(studentDto.getGender());
        student.setStudentPhone(studentDto.getPhone());
        student.setDateOfBirth(convertString.convertStringToDate(studentDto.getDateOfBirth()));
        student.setAddress(studentDto.getAddress());
        student.setClassroom(classroom);

        Roles roles = iRolesService.findRolesByName(studentDto.getStudentRole());
        if(roles==null){
            roles = new Roles(studentDto.getStudentRole());
            iRolesService.save(roles);
        }
        student.setRoles(roles);

        Parent parent ;
        if(student.getParent() == null){
            parent = new Parent();
        }else{
            parent =student.getParent();
        }
        parent.setParentName(parentDto.getParentName());
        parent.setPhone(parentDto.getParentPhone());
        parent.setAddress(parentDto.getParentAddress());
        parent.setDateOfBirth(convertString.convertStringToDate(parentDto.getParentDate()));
        parent.setStudent(student);
        iParentService.saveParent(parent);

        Account account;
        if(student.getAccount()==null){
            account = new Account();
        }else{
            account = student.getAccount();
            account.setEmail(null);
        }
        account.setEmail(studentDto.getStudentEmail());
        account.setPassword(passwordEncoder.encode(studentDto.getDateOfBirth()));
        account.setActive(true);

        Account accountNew = iAccountService.saveAccount(account);
        student.setAccount(accountNew);

        return student;
    }

    @Override
    public StudentDto getStudentDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setStudentId(student.getStudentId());
        studentDto.setName(student.getStudentName());
        studentDto.setGender(student.getGender());
        studentDto.setPhone(student.getStudentPhone());
        studentDto.setDateOfBirth(student.getDateOfBirth().toString());
        studentDto.setAddress(student.getAddress());
        studentDto.setClassName(student.getClassroom().getClassName());
        studentDto.setStudentEmail(student.getAccount().getEmail());
        return studentDto;
    }

    @Override
    public List<Student> findStudentByStudentNameAndClassroomId(String name,int classId) {
        return studentDao.findStudentByStudentNameAndClassroom_Id(name,classId);
    }

    @Override
    public List<Student> findStudentByGenderAndClassroomId(String gender,int classId) {
        return studentDao.findStudentByGenderAndClassroom_Id(gender,classId);
    }

    @Override
    public Student findStudentByAccountEmail(String email) {
        return studentDao.findStudentByAccount_Email(email);
    }
}
