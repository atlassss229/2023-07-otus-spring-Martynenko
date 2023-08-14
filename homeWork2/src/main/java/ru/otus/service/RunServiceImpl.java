package ru.otus.service;

import ru.otus.dao.CsvQuestionDao;
import ru.otus.dao.QuestionDao;
import ru.otus.dao.StudentDao;
import ru.otus.model.Answer;
import ru.otus.model.Question;
import ru.otus.model.Student;

import java.util.List;

public class RunServiceImpl implements RunService {

    private final QuestionDao questionDao;

    private final IOService ioService;

    private final Integer questionToPass;

    private final StudentDao studentDao;

    public RunServiceImpl(CsvQuestionDao csvQuestionDao,
                          IOService ioService,
                          StudentDao studentDao,
                          Integer questionToPass) {
        this.questionDao = csvQuestionDao;
        this.ioService = ioService;
        this.studentDao = studentDao;
        this.questionToPass = questionToPass;
    }

    @Override
    public void run() {
        Student student = studentDao.getStudent();
        testStudent(student);
        printTestingResult(student);
    }

    private void testStudent(Student student) {
        List<Question> questionList = questionDao.questionList();
        for (Question question : questionList) {
            ioService.printLine(question.getQuestion());
            printAnswersLine(question);
            Answer answer;
            List<Answer> answerList = question.getAnswerList();
            while (true) {
                int studentAnswer = ioService.readInt() - 1;
                if (studentAnswer >= 0 && studentAnswer < answerList.size()) {
                    answer = question.getAnswerList().get(studentAnswer);
                    break;
                } else {
                    ioService.printLine("please enter correct answer number");
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
        ioService.printLine(answersLine.toString());
    }

    private void printTestingResult(Student student) {
        ioService.printLine(student.getFirstName() + " " + student.getLastName());
        ioService.printLine("testing result:");
        if (student.getNumOfQuestionsAnswered() >= questionToPass) {
            ioService.printLine("Well done! You passed! Your score = "
                    + student.getNumOfQuestionsAnswered());
        } else {
            ioService.printLine("Something gone vert wrong. You didn't pass. Your score = "
                    + student.getNumOfQuestionsAnswered());
        }
    }
}

