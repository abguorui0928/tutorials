package action;

import org.apache.struts2.StrutsTestCase;

public class TestHelloworldAction extends StrutsTestCase {

	public void testExecute() throws Exception {
		executeAction("/helloworld.action");
		assertEquals("hello world!", request.getAttribute("welcome"));
	}
}
