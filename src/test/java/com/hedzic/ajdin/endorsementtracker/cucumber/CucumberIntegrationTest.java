package com.hedzic.ajdin.endorsementtracker.cucumber;

import com.hedzic.ajdin.endorsementtracker.EndorsementTrackerApplication;
import com.hedzic.ajdin.endorsementtracker.config.TestConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static com.hedzic.ajdin.endorsementtracker.config.SpringProfile.TESTING;

@SpringBootTest
@ContextConfiguration(classes = {EndorsementTrackerApplication.class, TestConfiguration.class})
@AutoConfigureMockMvc
@ActiveProfiles(TESTING)
public class CucumberIntegrationTest {

}