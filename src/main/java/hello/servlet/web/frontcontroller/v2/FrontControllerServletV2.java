package hello.servlet.web.frontcontroller.v2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

@WebServlet(name = "frontControllerServletv2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

	private Map<String, ControllerV2> controllerMap = new HashMap<>();
	
	public FrontControllerServletV2() {
		controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
		controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
		controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
		
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FrontControllerServletv2.service");
		
		String requestURI = request.getRequestURI();
	
		ControllerV2 controller = controllerMap.get(requestURI);
		if(controller == null ) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		//controller 자체가 이미 view 주소를 품고있음. map에서 꺼내는 행위자체가 주소 맵핑임
		//객체화임?
		MyView view = controller.process(request, response);
		//이동(렌더) 해줘
		view.render(request, response);
	}
	
	
}
