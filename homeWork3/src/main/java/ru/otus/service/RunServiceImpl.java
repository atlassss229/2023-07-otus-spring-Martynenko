package ru.otus.service;

import org.springframework.boot.CommandLineRunner;
import ru.otus.dao.CsvQuestionDao;
import ru.otus.dao.QuestionDao;
import ru.otus.dao.StudentDao;
import ru.otus.model.Answer;
import ru.otus.model.Question;
import ru.otus.model.Student;

import java.util.List;
import java.util.regex.Pattern;

public class RunServiceImpl implements CommandLineRunner {

    private final QuestionDao questionDao;

    private final IOService ioService;

    private final Integer questionToPass;

    private final StudentDao studentDao;

    private final MessageSourceService messageSourceService;

    private final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public RunServiceImpl(CsvQuestionDao csvQuestionDao,
                          IOService ioService,
                          StudentDao studentDao,
                          Integer questionToPass,
                          MessageSourceService messageSourceService) {
        this.questionDao = csvQuestionDao;
        this.ioService = ioService;
        this.studentDao = studentDao;
        this.questionToPass = questionToPass;
        this.messageSourceService = messageSourceService;
    }

    @Override
    public void run(String... args) {
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
            answer = getStudentAnswer(question, answerList);

            if (answer.isCorrect()) {
                student.setNumOfQuestionsAnswered(student.getNumOfQuestionsAnswered() + 1);
            }
        }
    }

    private Answer getStudentAnswer(Question question, List<Answer> answerList) {
        Answer answer;
        while (true) {
            String studentAnswer = ioService.readLine();
            if (isNumericAndShortEnough(studentAnswer)) {
                int answerIndex = Integer.parseInt(studentAnswer) - 1;
                if (answerIndex >= 0 && answerIndex < answerList.size()) {
                    answer = question.getAnswerList().get(answerIndex);
                    break;
                }
                ioService.printLine(messageSourceService.getMessage("input_error", null));
            } else {
                ioService.printLine(messageSourceService.getMessage("input_error", null));
            }
        }
        return answer;
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
        ioService.printLine(messageSourceService.getMessage("testing_result", null));
        if (student.getNumOfQuestionsAnswered() >= questionToPass) {
            ioService.printLine(messageSourceService.getMessage("positive_outcome", null)
                    + student.getNumOfQuestionsAnswered());
        } else {
            ioService.printLine(messageSourceService.getMessage("negative_outcome", null)
                    + student.getNumOfQuestionsAnswered());
        }
    }

    public boolean isNumericAndShortEnough(String strNum) {
        if (strNum == null || strNum.length() > 5) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }
}

