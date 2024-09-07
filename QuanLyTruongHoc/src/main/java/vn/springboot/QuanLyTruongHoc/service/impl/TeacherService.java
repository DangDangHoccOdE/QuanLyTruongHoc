package vn.springboot.QuanLyTruongHoc.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.springboot.QuanLyTruongHoc.dao.TeacherDao;
import vn.springboot.QuanLyTruongHoc.dto.TeacherDto;
import vn.springboot.QuanLyTruongHoc.entity.Account;
import vn.springboot.QuanLyTruongHoc.entity.Roles;
import vn.springboot.QuanLyTruongHoc.entity.Teacher;
import vn.springboot.QuanLyTruongHoc.service.inter.IAccountService;
import vn.springboot.QuanLyTruongHoc.service.inter.IRolesService;
import vn.springboot.QuanLyTruongHoc.service.inter.ITeacherService;
import vn.springboot.QuanLyTruongHoc.utils.ConvertString;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService implements ITeacherService {
    private final TeacherDao teacherDao;
    private final ConvertString convertString;
    private final IRolesService iRolesService;
    private final IAccountService iAccountService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public TeacherService(TeacherDao teacherDao, ConvertString convertString, IRolesService iRolesService, IAccountService iAccountService, PasswordEncoder passwordEncoder) {
        this.teacherDao = teacherDao;
        this.convertString = convertString;
        this.iRolesService = iRolesService;
        this.iAccountService = iAccountService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Teacher> showAllTeacher() {
        return teacherDao.findAll();
    }

    @Override
    @Transactional
    public void saveTeacher(Teacher teacher) {
         teacherDao.save(teacher);
    }

    @Override
    public Teacher findTeacherById(String id) {
        return teacherDao.findTeacherByTeacherId(id);
    }

    @Override
    @Transactional
    public void deleteTeacher(Teacher teacher) {
        teacherDao.delete(teacher);
    }

    @Override
    public Teacher findTeacherByAccountEmail(String email) {
        return teacherDao.findTeacherByAccount_Email(email);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherDao.findAll();
    }

    @Override
    public Teacher getTeacher(TeacherDto teacherDto, Teacher teacher) {
        teacher.setTeacherId(teacherDto.getId());
        teacher.setTeacherName(teacherDto.getName());
        teacher.setAddress(teacherDto.getAddress());
        teacher.setGender(teacherDto.getGender());
        teacher.setDateOfBirth(convertString.convertStringToDate(teacherDto.getDateOfBirth()));
        teacher.setTeacherPhone(teacherDto.getPhone());

        List<String> rolesName = teacherDto.getRolesName();
        List<Roles> roles = new ArrayList<>();

        for (String s : rolesName) {
            if(iRolesService.findRolesByName(s)==null){
                Roles rolesNew = new Roles(s);
                iRolesService.save(rolesNew);
            }
            roles.add(iRolesService.findRolesByName(s));
       }
        teacher.setRoles(roles);

        // Account
        Account account;
        if(teacher.getAccount()==null) {
            account = new Account();
        }else{
            account = teacher.getAccount();
            account.setEmail(null);
        }
        account.setEmail(teacherDto.getEmailTeacher());
        account.setPassword(passwordEncoder.encode(teacherDto.getDateOfBirth()));
        account.setActive(true);
        Account accountNew = iAccountService.saveAccount(account);

        teacher.setAccount(accountNew);

        return teacher;
    }

    @Override
    public TeacherDto getTeacherDto(Teacher teacher) {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(teacher.getTeacherId());
        teacherDto.setName(teacher.getTeacherName());
        teacherDto.setAddress(teacher.getAddress());
        teacherDto.setGender(teacher.getGender());
        teacherDto.setPhone(teacher.getTeacherPhone());
        teacherDto.setDateOfBirth(teacher.getDateOfBirth().toString());
        teacherDto.setEmailTeacher(teacher.getAccount().getEmail());

        List<String> rolesName = new ArrayList<>();
        List<Roles> rolesList = teacher.getRoles();
        for (Roles roles : rolesList) {
            String name = roles.getRoleName();
            rolesName.add(name);
        }

        teacherDto.setRolesName(rolesName);
        return teacherDto;
    }

    @Override
    public List<Teacher> findTeacherByTeachRole() {
        List<Teacher> teachers = teacherDao.findAll();
        List<Teacher> listTeachRole = new ArrayList<>();
        for(Teacher teacher : teachers){
            List<Roles> roles = teacher.getRoles();
            for(Roles role : roles){
                if(role.getRoleName().equals("ROLE_TEACHER")){
                    listTeachRole.add(teacher);
                }
            }

        }
        return listTeachRole;
    }

    @Override
    public String getEmailTeacher() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @Override
    public List<Teacher> findTeacherByTeacherName(String name) {
        return teacherDao.findTeacherByTeacherName(name);
    }

    @Override
    public boolean checkPrincipalByTeacher(Teacher teacher) {
        List<Roles> roles = teacher.getRoles();
        for(Roles roles1 : roles){
            if(roles1.getRoleName().equals("ROLE_PRINCIPAL")){
                return true;
            }
        }
        return false;
    }
}
