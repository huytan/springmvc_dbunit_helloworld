package springmvc.dbunit.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import springmvc.dbunit.entity.Person;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/applicationContextTest.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class PersonServiceTest {

    @Autowired
    private PersonService personService;
    
    @Test
    @DatabaseSetup("sampleData.xml")
    public void testFind() throws Exception {
        List<Person> personList = personService.find("hil");
        assertEquals(1, personList.size());
        assertEquals("Phillip", personList.get(0).getFirstName());
    }
    
    @Test
    @DatabaseSetup("sampleData.xml")
    @ExpectedDatabase("expectedData.xml")
    public void testRemove() throws Exception {
        personService.remove(1);
    }
}