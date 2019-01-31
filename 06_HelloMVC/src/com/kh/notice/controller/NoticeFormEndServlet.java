package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.notice.model.vo.Notice;
import com.kh.notice.service.NoticeService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class NoticeFormEndServlet
 */
@WebServlet("/notice/noticeFormEnd")
public class NoticeFormEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeFormEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//기본자료들을 다 그냥 가져올 수 있음
		//파일~!~! 을 가져오기 위해 cos.jar 라이브러리 사용
		//MultiPartRequest객체를 이용해서 파일을 업로드함
		//클라이언트에서 form으로 보낼때 데이터가 있으면 속성에 enctype을 설정해줘야함
		//enctype=multipart/formdata
		
		//파일 업로드 시작~~//
		//먼저 파일업로드 form으로 전송이 된것인지를 확인
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "공지 사항 오류[form:enctype 관리자에게 문의하세요]");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		//파일을 저장할 경로를 설정~!
		//서버에 있는 디렉토리에 저장을 해야하기 때문에 rootcontextpath의 절대 경로를 알아야함
		String root=getServletContext().getRealPath("/");
		//경로지정
		String saveDir = root+"upload/notice";
		
		//파일크기 제한 설정
		int maxSize = 1024*1024*10; //10Mb 크기
		
		//파일을 받아서 바로 저장하자
		//MultiPartRequest객체를 이용하면 됨 cos.jar에서 제공하는 객체로 이 객체를 생성하면 바로 보내진 파일이 업로드 된다.
		//생성할때 매개변수는
		//new MultiPartRequest(HttpServletRequest,String 파일저장경로, int 파일 최대크기, String 인코딩값(utf-8), Object 리네임설정객체);
		MultipartRequest mr = new MultipartRequest(request, saveDir,maxSize,"utf-8",new DefaultFileRenamePolicy());
		//이렇게 하면 프로젝트 리프레쉬하면 upload->notice에 파일이 업로드됨
		
		String title = mr.getParameter("title");
		String writer = mr.getParameter("writer");
		String content = mr.getParameter("content");
		
		//파일이름
		String fileName = mr.getFilesystemName("upfile");
		
		Notice n = new Notice();
		n.setNoticeContect(content);
		n.setNoticeTitle(title);
		n.setNoticeWriter(writer);
		n.setFilePath(fileName);
		
		int rs = new NoticeService().upNotice(n);
		if(rs>0) {
			System.out.println("저장성공");
			request.setAttribute("msg", "공지사항등록 성공");
			request.setAttribute("loc", "/notice/noticeList");
		}
			
		else {
			System.out.println("저장실패");
			request.setAttribute("msg", "공지사항등록 실패");
			request.setAttribute("loc", "/views/notice/noticeForm.jsp");
		}
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
