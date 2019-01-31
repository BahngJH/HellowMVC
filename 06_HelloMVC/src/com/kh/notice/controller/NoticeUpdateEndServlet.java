package com.kh.notice.controller;

import java.io.File;
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
 * Servlet implementation class NoticeUpdateEndServlet
 */
@WebServlet("/notice/updateNoticeEnd")
public class NoticeUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "파일형식이 맞지않습니다 multipart/관리자에게 문의하세요");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		
		String root = getServletContext().getRealPath("/");
		String saveDir = root+"upload/notice";
		int maxSize = 1024*1024*10;
		
		//이게 실행되었을때 이미 서버에 파일이 저장된 상태이다.
		MultipartRequest mr = new MultipartRequest(request, saveDir,maxSize,"utf-8",new DefaultFileRenamePolicy());
		
		String title = mr.getParameter("title");
		String writer = mr.getParameter("writer");
		String content = mr.getParameter("content");
		int no = Integer.parseInt(mr.getParameter("no"));
		
		//이전파일 불러오기, 이름만 불러오는것이다 파일 자체는 아니다.
		String oldFile = mr.getParameter("old_file");
		
		//저장할 파일 불러오기
		String newFile = mr.getFilesystemName("upfile");
		
		//실제 파일을 불러와서 그 파일을 비교
		File f = mr.getFile("upfile");
		if(f!=null && f.length()>0) //파일이 있는지 없는지 검사
		{
			//경로와 파일이름을 new File 매개변수로 넣어줘서 deleteFile로 뽑아냄
			File deleteFile = new File(saveDir+"/"+oldFile);
			
			//삭제되면 true, 안되면 false 리턴
			boolean bool = deleteFile.delete();
			
			System.out.println(bool?"파일삭제성공":"파일삭제실패");
		}else {
			newFile = oldFile;
		}
		
		Notice n = new Notice();
		n.setNoticeNo(no);
		n.setNoticeTitle(title);
		n.setNoticeWriter(writer);
		n.setNoticeContect(content);
		n.setFilePath(newFile);
		
		String msg="";
		String loc="";
		String view="/views/common/msg.jsp";
		
		int rs = new NoticeService().updateNotice(n);
		if(rs>0) {
			//변경성공
			msg="공지사항 수정 성공!";
			//요청값을 가지고 있는 그쪽으로 보내준다.
			loc="/notice/noticeView?no="+no;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
