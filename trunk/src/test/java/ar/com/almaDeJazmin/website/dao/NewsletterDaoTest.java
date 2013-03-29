package ar.com.almaDeJazmin.website.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.almaDeJazmin.website.AbstractTest;
import ar.com.almaDeJazmin.website.domain.Newsletter;
import ar.com.almaDeJazmin.website.domain.NewsletterDistribution;

public class NewsletterDaoTest extends AbstractTest {
	
	@Autowired
	private NewsletterDao newsletterDao;
	
	@Test
	public void testGetById() {
		
		Newsletter newsletter = buildNewsletter();
		newsletter = newsletterDao.create(newsletter);
		Assert.assertNotNull(newsletter.getId());
		
		Newsletter found = newsletterDao.getById(newsletter.getId());
		Assert.assertEquals(newsletter.getContent(), found.getContent());
		Assert.assertEquals(newsletter.getName(), found.getName());
		Assert.assertEquals(newsletter.getSubject(), found.getSubject());
		
		newsletterDao.delete(newsletter);
		
		found = newsletterDao.getById(found.getId());
		Assert.assertNull(found);
	}
	
	@Test
	public void testGetByName() {
		
		Newsletter newsletter = buildNewsletter();
		newsletter = newsletterDao.create(newsletter);
		
		Newsletter found = newsletterDao.getByName(newsletter.getName());
		Assert.assertEquals(newsletter, found);
		
		newsletterDao.delete(newsletter);
		
		found = newsletterDao.getByName(found.getName());
		Assert.assertNull(found);
	}
	
	@Test
	public void testGetAll() {
		
		Newsletter newsletter1 = buildNewsletter();
		newsletter1 = newsletterDao.create(newsletter1);
		
		Newsletter newsletter2 = buildNewsletter();
		newsletter2.setName("other name");
		newsletter2 = newsletterDao.create(newsletter2);
		
		List<Newsletter> newsletters = newsletterDao.getAll();
		Assert.assertEquals(2, newsletters.size());
		Assert.assertTrue(newsletters.contains(newsletter1));
		Assert.assertTrue(newsletters.contains(newsletter2));
		
		newsletterDao.delete(newsletter1);
		newsletterDao.delete(newsletter2);
		
		newsletters = newsletterDao.getAll();
		Assert.assertEquals(0, newsletters.size());
		
	}
	
	@Test
	public void testCreateNewsletterWithDistribution() {
		Newsletter newsletter = buildNewsletter();
		newsletter.addDistribution(buildDistribution());
		newsletter = newsletterDao.create(newsletter);
		
		Newsletter found = newsletterDao.getById(newsletter.getId());
		Assert.assertEquals(1, found.getDistributions().size());
		Assert.assertEquals(newsletter.getDistributions().get(0).getContactType(), 
				found.getDistributions().get(0).getContactType());
		Assert.assertEquals(newsletter.getDistributions().get(0).getFailures(), 
				found.getDistributions().get(0).getFailures());
		Assert.assertEquals(newsletter.getDistributions().get(0).getSentDate(), 
				found.getDistributions().get(0).getSentDate());
		Assert.assertEquals(newsletter.getDistributions().get(0).getSuccess(), 
				found.getDistributions().get(0).getSuccess());
		
		newsletterDao.delete(newsletter);
	}

	private NewsletterDistribution buildDistribution() {
		NewsletterDistribution distribution = new NewsletterDistribution();
		distribution.setContactType("contactType");
		distribution.setFailures(2);
		distribution.setSuccess(1200);
		return distribution;
	}

	private Newsletter buildNewsletter() {
		Newsletter newsletter = new Newsletter();
		newsletter.setContent("<html><body><div>content</div></body></html>");
		newsletter.setName("name");
		newsletter.setSubject("subject");
		return newsletter;
	}

}
