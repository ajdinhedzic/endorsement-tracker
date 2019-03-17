package com.hedzic.ajdin.endorsementtracker;

import com.hedzic.ajdin.endorsementtracker.config.TestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {EndorsementTrackerApplication.class, TestConfiguration.class})
@SpringBootTest
public class EndorsementTrackerApplicationTests {

    @Test
    public void contextLoads() {
    }

}

