package hello.servlet.web.frontcontroller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//view경로를 저장해서 처리하는 뭔가를 처리해줄 객체
public class MyView {
	private String viewPath;

	public MyView(String viewPath) {
		this.viewPath = viewPath;
	}
	//요청한 view로 이동(그려)시켜줄께
	public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
		dispatcher.forward(request, response);
	}
	//요청한 view로 이동(그려)시켜줄께 + 데이터를 가지고 이동
	public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		modelToRequestAttribute(model, request);
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
		dispatcher.forward(request, response);		
	}
	//hashmap으로 가져온 데이터들을 하나씩 request객체에 넣는다.
	private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
		model.forEach((key,value)-> request.setAttribute(key, value));
	}
	
	
	
	

}
