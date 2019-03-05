package com.hedzic.ajdin.endorsementtracker.cucumber;

import com.hedzic.ajdin.endorsementtracker.EndorsementTrackerApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = EndorsementTrackerApplication.class)
@AutoConfigureMockMvc
public class CucumberIntegrationTest {
}
