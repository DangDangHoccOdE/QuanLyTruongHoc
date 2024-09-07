package vn.springboot.QuanLyTruongHoc.utils;

import org.springframework.stereotype.Component;
import vn.springboot.QuanLyTruongHoc.entity.Classroom;
import vn.springboot.QuanLyTruongHoc.entity.Subject;
import vn.springboot.QuanLyTruongHoc.entity.Teacher;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CheckAccessClassroom {
    public boolean hasNotAccessClassroom(Integer classId, Teacher teacher) {
        List<Classroom> classrooms = getClassroomsByTeacherAccess(teacher);
        for (Classroom classroom : classrooms) {
            if (classroom.getId() == classId) {
                return false;
            }
        }
        return true;
    }

    public List<Classroom> getClassroomsByTeacherAccess(Teacher teacher) {
        Set<Classroom> uniqueClassrooms = findClassroomByTeachSubject(teacher);

        List<Classroom> classroomsByHomeroomTeacher = teacher.getClassRooms();

        List<Classroom> classrooms = new ArrayList<>(uniqueClassrooms);
        for(Classroom classroom : classroomsByHomeroomTeacher){
            if(!classrooms.contains(classroom)){
                classrooms.add(classroom);
            }
        }

        return classrooms;
    }

    private Set<Classroom> findClassroomByTeachSubject(Teacher teacher) {
        List<Subject> subjects = teacher.getSubjects();
        Set<Classroom> uniqueClassrooms = new HashSet<>();

        for (Subject subject : subjects) {
            List<Classroom> classrooms = subject.getClassrooms();
            uniqueClassrooms.addAll(classrooms);
        }

        return uniqueClassrooms;
    }

}
