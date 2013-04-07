package ohtu.mini.repository;

import ohtu.mini.domain.SampleObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context-test.xml",
    "classpath:spring-database-test.xml"})
public class SampleObjectRepositoryTest {

	@Autowired
	SampleObjectRepository sor;

	@Test
	public void testAddingSampleObjectToDb() {
		long countAtStart = sor.count();
		SampleObject object = new SampleObject();
		object.setAwesome(true);
		sor.save(object);
		long countAtEnd = sor.count();
		Assert.assertTrue("Adding failed..", countAtStart+1 == countAtEnd);
	}


	@Test
	public void testAddMultiple() {
		SampleObject so;
		for (int i=0; i<10; i++) {
			so = new SampleObject();
			so.setAwesome(Math.random()>0.5);
			sor.save(so);
		}
		Assert.assertTrue(sor.count() >= 10);
	}

	@Test
	public void testFindById() {
		Assert.assertTrue(sor.findById(2) != null);
	}

	@Test
	public void testFindByAwesome() {
		Assert.assertTrue(sor.findByAwesome(false).size() >= 1);
	}

}
