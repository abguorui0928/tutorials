package org.ebean;

import java.util.Calendar;

import org.ebean.entity.User;
import org.junit.Assert;
import org.junit.Test;

import com.avaje.ebean.Ebean;

public class App {

	@Test
	public void testSave() {
		User u = new User();
		u.setId(121);
		u.setActive(User.ACTIVE);
		u.setUserName("guor");
		u.setDisplayName("郭锐");
		u.setEmailAddress("abguorui0928@163.com");
		u.setCreateDate(Calendar.getInstance());
		u.setUpdateDate(Calendar.getInstance());
		u.setCredential("123456");

		User oldUser = Ebean.find(User.class, u.getId());
		Assert.assertNull(oldUser);

		Ebean.save(u);

		oldUser = Ebean.find(User.class, u.getId());
		Assert.assertNotNull(oldUser);

		Ebean.delete(oldUser);

		oldUser = Ebean.find(User.class, u.getId());
		Assert.assertNull(oldUser);
	}
}
