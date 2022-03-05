package cz.moneta.algorithm.service;

import cz.moneta.algorithm.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlgorithmServiceTest {

    @Autowired
    AlgorithmService algorithmService;

    @Test
    public void testService1() {
        String finalNumber = algorithmService.getNumber("43256791");
        assertThat("", finalNumber.equals("11331545"));
    }

    @Test
    public void testService2() {
        String finalNumber = algorithmService.getNumber("43256797");
        assertThat("", finalNumber.equals("1133154"));
    }

    @Test
    public void testService3() {
        String finalNumber = algorithmService.getNumber("33256791");
        assertThat("", finalNumber.equals("17775393"));
    }

    @Test
    public void testService4() {
        String finalNumber = algorithmService.getNumber("100");
        assertThat("", finalNumber.equals("5"));
    }
}
