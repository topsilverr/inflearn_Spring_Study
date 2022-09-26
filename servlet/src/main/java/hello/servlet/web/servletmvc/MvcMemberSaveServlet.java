package hello.servlet.web.servletmvc;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="mvcMemberSaveServlet", urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        //String age = request.getParameter("age");
        // request.getParameter 의 결과는 항상 문자! 따라서 String으로 선언해야됨 => int 로 변환 필요
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username,age);
        memberRepository.save(member);

        //Model에 데이터를 보관한다
        request.setAttribute("member",member);

        //view로 넘기기
        String viewPath = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher dispatcher= request.getRequestDispatcher(viewPath);
        dispatcher.forward(request,response);

    }
}
