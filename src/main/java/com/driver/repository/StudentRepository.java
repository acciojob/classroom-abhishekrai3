package com.driver.repository;

import java.util.*;
import com.driver.model.Student;
import com.driver.model.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    private HashMap<String, Student> studentMap;
    private HashMap<String, Teacher> teacherMap;
    private HashMap<String, List<String>> teacherStudentMapping;

    public StudentRepository(){
        this.studentMap = new HashMap<String, Student>();
        this.teacherMap = new HashMap<String, Teacher>();
        this.teacherStudentMapping = new HashMap<String, List<String>>();
    }

    public void saveStudent(Student student){
        String name= student.getName();
        studentMap.put(name, student);
    }

    public void saveTeacher(Teacher teacher){
        String name= teacher.getName();
        teacherMap.put(name, teacher);
    }

    public void saveStudentTeacherPair(String student, String teacher){
        if (!teacherStudentMapping.containsKey(teacher)) {
            teacherStudentMapping.put(teacher, new ArrayList<>());
        }
        teacherStudentMapping.get(teacher).add(student);
    }

    public Student findStudent(String student){
         if(studentMap.containsKey(student)){
             return studentMap.get(student);
         }
         return null;
    }

    public Teacher findTeacher(String teacher){
        // your code goes here
        if(teacherMap.containsKey(teacher)){
            return teacherMap.get(teacher);
        }
        return null;
    }

    public List<String> findStudentsFromTeacher(String teacher){
        List<String> orDefault = teacherStudentMapping.getOrDefault(teacher, new ArrayList<>());
        return orDefault;
    }

    public List<String> findAllStudents(){
        return new ArrayList<>(studentMap.keySet());
    }

    public void deleteTeacher(String teacher){

        teacherMap.remove(teacher);
        teacherStudentMapping.remove(teacher);
    }

    public void deleteAllTeachers(){
        teacherMap.clear();
        studentMap.clear();
        teacherStudentMapping.clear();
    }
}