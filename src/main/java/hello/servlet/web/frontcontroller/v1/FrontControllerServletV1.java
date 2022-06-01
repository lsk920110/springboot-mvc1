package hello.servlet.web.frontcontroller.v1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;


//최초진입 컨트롤러 모든 요청을 다 받는다.
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {
	
	private Map<String, ControllerV1> controllerMap = new HashMap<>();
	
	
	//생성자 : key = 요청명 / value = 해당요청시 실행할 컨트롤러들을 객체화
	public FrontControllerServletV1() {
		controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
		controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
		controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
		
	}
	//frontcontroller 진입할때 이루어지는 작업(서비스)
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FrontControllerServletV1.service");
		
		//어떤 요청으로 들어왔는지 꺼내서 확인한다.
		String requestURI = request.getRequestURI();
	
		//현재 요청명에 해당하는 controller 객체를 꺼내온다.
		ControllerV1 controller = controllerMap.get(requestURI);
		//컨트롤러가 존재하지 않으면 == uri요청이 잘못들어왔다면,
		//404에러를 처리한다.
		if(controller == null ) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		//현재 요청,응답 객체(결과를) 꺼내온 컨트롤러에 넣는다.
		controller.process(request, response);
	
	}
	
	
}
