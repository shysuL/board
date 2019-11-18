package web.service.impl;

import javax.servlet.http.HttpServletRequest;

import web.dao.face.MemberDao;
import web.dao.impl.MemberDaoImpl;
import web.dto.Member;
import web.service.face.MemberService;

public class MemberServiceImpl implements MemberService{

	MemberDao memberDao = new MemberDaoImpl();
	@Override
	public Member getLoginMember(HttpServletRequest req) {
		
		Member member = new Member();		
		
		String param = null;
				
		param = req.getParameter("userid");
		if (param != null && !"".equals(param)) {
			member.setUserid(param);
		}
		param = req.getParameter("userpw");
		if (param != null && !"".equals(param)) {
			member.setUserpw(param);
		}
		

		
		
		System.out.println("[MemberService] member : " + member );
				
		return member;
	}

	@Override
	public boolean login(Member member) {
		
		int cnt = 0;
		cnt = memberDao.selectCntMemberByUserid(member);
		System.out.println("CNT : " + memberDao.selectCntMemberByUserid(member));
		if(cnt == 1) {
			System.out.println("login()" + cnt);
			return true;
		} 
		return false;
	}

	@Override
	public Member getMemberByUserid(Member member) {
		
		return memberDao.selectMemberByUserid(member);
	}

	@Override
	public void join(Member member) {
		memberDao.insert(member);
	}

	@Override
	public Member getMemberParam(HttpServletRequest req) {
		Member member = new Member();
		
		String param = null;
		
		// userid
		param = req.getParameter("userid");
		if (param != null && !"".equals(param)) {
			member.setUserid(param);
		}
		// userpw
		param = req.getParameter("userpw");
		if (param != null && !"".equals(param)) {
			member.setUserpw(param);
		}
		// usernick
		param = req.getParameter("usernick");
		if (param != null && !"".equals(param)) {
			member.setUsernick(param);
		}
		
		
		return member;
	}
	
	

}
