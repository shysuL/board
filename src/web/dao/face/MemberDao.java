package web.dao.face;

import web.dto.Member;

public interface MemberDao {
	
	public int selectCntMemberByUserid(Member member);
	
	public Member selectMemberByUserid(Member member);

	
	public void insert(Member member);
}
