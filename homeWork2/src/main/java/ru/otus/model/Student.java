package ru.otus.model;

public class Student {

    private final String firstName;

    private final String lastName;

    private int numOfQuestionsAnswered = 0;


    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setNumOfQuestionsAnswered(int numOfQuestionsAnswered) {
        this.numOfQuestionsAnswered = numOfQuestionsAnswered;
    }

    public int getNumOfQuestionsAnswered() {
        return numOfQuestionsAnswered;
    }
}
