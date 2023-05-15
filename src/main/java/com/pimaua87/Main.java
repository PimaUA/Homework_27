package com.pimaua87;

import dao.StudentDao;
import entity.Student;

public class Main {

    public static void main(String[] args) {
        StudentDao studentDao = new StudentDao();

        studentDao.getByID(2);
        studentDao.getAll();
        studentDao.add(new Student("George", "george.gmail.com"));
        studentDao.update(new Student(1, "Jim", "jim.gmail.com"));
        studentDao.delete(new Student(11, "George"));
    }
}
