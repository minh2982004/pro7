package controller;

import view.MenuView;
import view.MenuModel;
import model.Report;
import model.Student;
import model.Validation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Manager {

    private MenuModel model;
    private MenuView view;

    public Manager(MenuModel model, MenuView view) {
        this.model = model;
        this.view = view;
    }

    //Show menu
    public void menu() {
        ArrayList<Student> ls = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            view.Menu();
            int choice = view.UserChoice();
            int count = 0;

            switch (choice) {
                case 1:
                    createStudent(count, ls);
                    break;
                case 2:
                    findAndSort(ls);
                    break;
                case 3:
                    updateOrDelete(count, ls);
                    break;
                case 4:
                    report(ls);
                    break;
                case 5:
                    System.out.println("Exiting the program.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void createStudent(int count, ArrayList<Student> ls) {
        for (int i = 0; i <= 10; i++) {
            if (count >= 10) {
                System.out.print("Do you want to continue (Y/N)? ");
                if (!Validation.checkInputYN()) {
                    break;
                }
            }

            System.out.print("Enter id: ");
            String id = Validation.checkInputString();
            System.out.print("Enter name student: ");
            String name = Validation.checkInputString();

            if (!Validation.checkIdExist(ls, id, name)) {
                System.err.println("Id has exist student. Please re-input.");
                continue;
            }

            System.out.print("Enter semester: ");
            String semester = Validation.checkInputString();
            System.out.print("Enter name course: ");
            String course = Validation.checkInputCourse();

            if (Validation.checkStudentExist(ls, id, name, semester, course)) {
                ls.add(new Student(id, name, semester, course));
                count++;
                System.out.println("Add student success.");
            } else {
                System.err.println("Duplicate.");
            }
        }
    }

    public static void findAndSort(ArrayList<Student> ls) {

        if (ls.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        ArrayList<Student> listStudentFindByName = listStudentFindByName(ls);
        if (listStudentFindByName.isEmpty()) {
            System.err.println("Not exist.");
        } else {
            Collections.sort(listStudentFindByName);
            System.out.printf("%-15s%-15s%-15s\n", "Student name", "semester", "Course Name");

            for (Student student : listStudentFindByName) {
                student.print();
            }
        }
    }

    //List student found by name
    public static ArrayList<Student> listStudentFindByName(ArrayList<Student> ls) {
        ArrayList<Student> listStudentFindByName = new ArrayList<>();
        System.out.print("Enter name to search: ");
        String name = Validation.checkInputString();
        for (Student student : ls) {

            if (student.getStudentName().contains(name)) {
                listStudentFindByName.add(student);
            }
        }
        return listStudentFindByName;
    }

    public static void updateOrDelete(int count, ArrayList<Student> ls) {

        if (ls.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        System.out.print("Enter id: ");
        String id = Validation.checkInputString();
        ArrayList<Student> listStudentFindByName = getListStudentById(ls, id);

        if (listStudentFindByName.isEmpty()) {
            System.err.println("Not found student.");
            return;
        } else {
            Student student = getStudentByListFound(listStudentFindByName);
            System.out.print("Do you want to update (U) or delete (D) student: ");
            //check user want to update or delete
            if (Validation.checkInputUD()) {
                System.out.print("Enter name student: ");
                String name = Validation.checkInputString();
                System.out.print("Enter semester: ");
                String semester = Validation.checkInputString();
                System.out.print("Enter name course: ");
                String course = Validation.checkInputCourse();
                //check student exist or not
                if (Validation.checkStudentExist(ls, id, name, semester, course)) {
                    student.setStudentName(name);
                    student.setSemester(semester);
                    student.setCourseName(course);
                    System.err.println("Update success.");
                }
                return;
            } else {
                ls.remove(student);
                count--;
                System.err.println("Delete success.");
                return;
            }
        }
    }

    public static Student getStudentByListFound(ArrayList<Student> listStudentFindByName) {
        System.out.println("List student found: ");
        int count = 1;
        System.out.printf("%-10s%-15s%-15s%-15s\n", "Number", "Student name",
                "semester", "Course Name");

        for (Student student : listStudentFindByName) {
            System.out.printf("%-10d%-15s%-15s%-15s\n", count,
                    student.getStudentName(), student.getSemester(),
                    student.getCourseName());
            count++;
        }
        System.out.print("Enter student: ");
        int choice = Validation.checkInputIntLimit(1, listStudentFindByName.size());
        return listStudentFindByName.get(choice - 1);
    }

    public static ArrayList<Student> getListStudentById(ArrayList<Student> ls, String id) {
        ArrayList<Student> getListStudentById = new ArrayList<>();
        for (Student student : ls) {
            if (id.equalsIgnoreCase(student.getId())) {
                getListStudentById.add(student);
            }
        }
        return getListStudentById;
    }

    public static void report(ArrayList<Student> ls) {
        if (ls.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        ArrayList<Report> lr = new ArrayList<>();
        int size = ls.size();
        for (int i = 0; i < size; i++) {
            int total = 0;
            for (Student student1 : ls) {
                for (Student student2 : ls) {
                    if (student1.getId().equalsIgnoreCase(student2.getId())
                            && student1.getCourseName().equalsIgnoreCase(student2.getCourseName())) {
                        total++;
                    }
                }
                if (Validation.checkReportExist(lr, student1.getStudentName(),
                        student1.getCourseName(), total)) {
                    lr.add(new Report(student1.getStudentName(),
                            student1.getCourseName(), total));
                }
            }
        }

        for (int i = 0; i < lr.size(); i++) {
            System.out.printf("%-15s|%-10s|%-5d\n", lr.get(i).getStudentName(),
                    lr.get(i).getCourseName(), lr.get(i).getTotalCourse());
        }
    }
}
