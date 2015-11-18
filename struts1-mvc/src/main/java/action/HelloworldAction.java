package action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class HelloworldAction extends Action {

	static int count = 0;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("welcome", "hello world!");

		Map<String, Cookie> cookies = cookies2Map(request.getCookies());
		if (!cookies.containsKey("hac")) {
			Cookie cookie = new Cookie("hac", "guor" + System.currentTimeMillis());
			System.out.println(count++ + ":::" + cookie.getName() + ":" + cookie.getValue());
			response.addCookie(cookie);
		} else {
			Cookie cookie = cookies.get("hac");
			System.out.println(count++ + ":::" + cookie.getName() + ":" + cookie.getValue());
		}
		return mapping.findForward("index");
	}

	static Map<String, Cookie> cookies2Map(Cookie[] cookies) {
		Map<String, Cookie> map = new HashMap<String, Cookie>();
		if (cookies == null) {
			return map;
		}
		for (Cookie cookie : cookies) {
			map.put(cookie.getName(), cookie);
		}
		return map;
	}
}
