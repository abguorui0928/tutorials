package action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class HelloworldAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8842675502521624758L;

	//helloworld.action
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("welcome", "hello world!");
		return "success";
	}
}
