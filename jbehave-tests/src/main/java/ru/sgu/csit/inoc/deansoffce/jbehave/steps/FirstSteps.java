package ru.sgu.csit.inoc.deansoffce.jbehave.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.junit.Assert.assertTrue;

public class FirstSteps {

    private String message;

    @Given("message $message")
    public void message(String message) {
        this.message = message;
    }

    @When("I welcome $entity")
    public void welcome(String entity) {
        message += entity;
    }

    @Then("I say $message")
    public void say(String message) {
        assertTrue(this.message.equals(message));
    }
}
