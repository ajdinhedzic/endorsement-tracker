package com.hedzic.ajdin.endorsementtracker.cucumber;

import com.hedzic.ajdin.endorsementtracker.entity.Pilot;
import com.hedzic.ajdin.endorsementtracker.repository.PilotRepository;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class InstructorSignUpStepDefinitions extends CucumberIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PilotRepository pilotRepository;
    private MockHttpServletResponse response;

    @When("^instructor signs up$")
    public void instructor_signs_up() throws Throwable {
        response = requestWith("/api/signup", "admin");;
    }

    @Then("^there is a pilot profile created$")
    public void there_is_a_pilot_profile_created() throws Throwable {
        Pilot pilot = pilotRepository.findAll().iterator().next();
        assertEquals(1, pilotRepository.count());
        assertEquals(1, pilot.getUserAccount().getId());
        assertEquals("hello@hello.com", pilot.getUserAccount().getEmail());
    }

    @Given("^an instructor is in the database$")
    public void anInstructorIsInTheDatabase() throws Exception {
        response = requestWith("/api/signup", "admin");
    }

    @Then("^a (\\d+) status is returned$")
    public void aStatusIsReturned(int httpStatus) {
        assertEquals(httpStatus, response.getStatus());
    }

    @When("^the instructor logs in$")
    public void theInstructorLogsIn() throws Exception {
        response = requestWith("/login", "admin");
    }

    @When("^the instructor logs in with an incorrect password$")
    public void theInstructorLogsInWithAnIncorrectPassword() throws Exception {
        response = requestWith("/login", "WRONG PASSWORD");
    }

    private MockHttpServletResponse requestWith(String url, final String password) throws Exception {
        return mockMvc.perform(post(url)
                .contentType("application/json")
                .content("{\"email\":\"hello@hello.com\", \"password\":\"" + password + "\"}"))
                .andReturn()
                .getResponse();
    }
}