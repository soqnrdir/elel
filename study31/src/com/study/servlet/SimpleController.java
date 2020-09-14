package com.study.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.free.web.FreeDeleteController;
import com.study.free.web.FreeEditController;
import com.study.free.web.FreeFormController;
import com.study.free.web.FreeListController;
import com.study.free.web.FreeModifyController;
import com.study.free.web.FreeRegistController;
import com.study.free.web.FreeViewController;

public class SimpleController extends HttpServlet {

	public static void main(String[] args) {
		String cp = "/study3";
		String t = "/study3/free/freeList.wow";
		t = t.substring(cp.length());
		System.out.println(t);

	}

	// IFreeBoardService freeBoardService = new FreeBoardServiceImpl();
	// ICommonCodeService codeService = new CommonCodeServiceImpl();

	// 1. 클라이언트의 요청을 받는다.
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 공통기능 처리
		req.setCharacterEncoding("utf-8");

		// 2. 클라이언트 요청 분석 (uri)
		String uri = req.getRequestURI(); // /study3/free/freeList.wow
		uri = uri.substring(req.getContextPath().length()); // /free/freeList.wow
		System.out.println("uri=" + uri);

		String viewPage = "";
		IController controller = null;
		// 3. 모델을 사용하여 처리
		// 4. 결과를 속성에 저장
		if ("/free/freeList.wow".equals(uri)) {
			controller = new FreeListController();
		} else if ("/free/freeView.wow".equals(uri)) {
			controller = new FreeViewController();
		} else if ("/free/freeForm.wow".equals(uri)) {
			controller = new FreeFormController();
		} else if ("/free/freeRegist.wow".equals(uri)) {
			controller = new FreeRegistController();
		} else if ("/free/freeEdit.wow".equals(uri)) {
			controller = new FreeEditController();
		} else if ("/free/freeModify.wow".equals(uri)) {
			controller = new FreeModifyController();
		} else if ("/free/freeDelete.wow".equals(uri)) {
			controller = new FreeDeleteController();
		}

		if (controller != null) {
			try {
				viewPage = controller.process(req, resp);
				System.out.println("viewPage=" + viewPage);

				// 5. 뷰페이지로 이동
				RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
				dispatcher.forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		} else {
			// 요청에 대한 컨트롤러가 존재하지 않으므로 404 던지기
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, uri + "에 해당하는 페이지가 존재하지 않습니다.");
		}
	}

}
