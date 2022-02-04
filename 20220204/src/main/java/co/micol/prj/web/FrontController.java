package co.micol.prj.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.comm.Command;
import co.micol.prj.command.Loginform;
import co.micol.prj.command.MainCommand;
import co.micol.prj.command.RegisterForm;







//@WebServlet("/")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HashMap<String,Command> map;
   
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	public void init(ServletConfig config) throws ServletException {
		map = new HashMap<String, Command>(); //map 초기화
		map.put("/main.do", new MainCommand()); //메인페이지
		map.put("/loginform.do", new Loginform());//로그인폼 호출
		map.put("/registerForm.do", new RegisterForm());//회원가입폼
	}

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String page = uri.substring(contextPath.length());
		
		Command command = map.get(page);
		String viewPage = command.exec(request, response);
		
		if(!viewPage.endsWith(".do")){
			viewPage = viewPage +".tiles";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
