package ru.otus.service;

import ru.otus.model.Answer;
import ru.otus.model.Question;
import ru.otus.model.Student;

import java.util.List;
import java.util.Scanner;

public class RunServiceImpl implements RunService {

    private final TestCreatorService testCreatorService;

    private final Integer questionToPass;

    public RunServiceImpl(TestCreatorServiceImpl testCreatorServiceImpl,
                          Integer questionToPass) {
        this.testCreatorService = testCreatorServiceImpl;
        this.questionToPass = questionToPass;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        Student student = getStudentName(scanner);
        testStudent(scanner, student);
        printTestingResult(student);
    }


    private Student getStudentName(Scanner scanner) {

        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        return new Student(firstName, lastName);
    }

    private void testStudent(Scanner scanner, Student student) {
        List<Question> questionList = testCreatorService.createTest();
        for (Question question : questionList) {
            System.out.println(question.getQuestion());
            printAnswersLine(question);
            Answer answer;
            while (true) {
                try {
                    int studentAnswer = Integer.parseInt(scanner.nextLine()) - 1;
                    answer = question.getAnswerList().get(studentAnswer);
                    break;
                } catch (Exception e) {
                    System.out.println("please enter correct answer number");
                }
            }
            if (answer.isCorrect()) {
                student.setNumOfQuestionsAnswered(student.getNumOfQuestionsAnswered() + 1);
            }
        }
    }

    private void printAnswersLine(Question question) {
        StringBuilder answersLine = new StringBuilder();
        int count = 1;
        for (Answer answer : question.getAnswerList()) {
            answersLine.append(count).append(")").append(" ").append(answer.getValue()).append("; ");
            count++;
        }
        System.out.println(answersLine);
    }

    private void printTestingResult(Student student) {
        System.out.println(student.getFirstName() + " " + student.getLastName());
        System.out.println("testing result:");
        if (student.getNumOfQuestionsAnswered() >= questionToPass) {
            System.out.println("Well done! You passed! Your score = "
                    + student.getNumOfQuestionsAnswered());
        } else {
            System.out.println("Something gone vert wrong. You didn't pass. Your score = "
                    + student.getNumOfQuestionsAnswered());
        }
    }
}

