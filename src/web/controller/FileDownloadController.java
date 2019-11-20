package web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import web.dto.Boardfile;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;

@WebServlet("/file/download")
public class FileDownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardService boardservice = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Boardfile downFile = boardservice.getFile(req);
		
		// 다운로드용 File객체 만들기
		String path = getServletContext().getRealPath("upload"); // 경로
		String filename = downFile.getStoredname() ; // 파일이름
		
		File file = new File(path,filename);
				
		
		// - - - 파일 다운로드 시키기 - - -
		// 응답 정보 객체를 설정한다
		// Response Message의 Header를 수정한다

		// 응답 body의 길이 설정
		resp.setHeader("Content-Length", String.valueOf(file.length()));
		
		// 응답 데이터의 형식(MIME-Type)
		resp.setContentType("application/octet-stream"); // 이진 파일의 형태
		
		
		resp.setHeader("Content-Dispposition",
	       "attachment;fileName=" + new String(downFile.getOriginname().getBytes("utf-8"), "8859_1") + ";");
		
		

		// - - - 응답 메세지의 응답 Body(본문) 작성 - - -
		// 파일의 내용을 응답으로 출력

		// 파일 입력 스트림(서버의 로컬 저장소 파일)
		InputStream is = new FileInputStream(file);

		// 파일 출력 스트림( 브라우저 )
		OutputStream os = resp.getOutputStream();

		// 파일 입력 -> 브라우저 출력
		IOUtils.copy(is, os);

		os.flush();

		is.close();
		os.close();
	}
}
