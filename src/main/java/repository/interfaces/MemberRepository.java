package repository.interfaces;

 
import java.util.List;

import model.Member;

public interface MemberRepository {
	
	public List<Member> getMembersBySquad(long id);
	public List<Member> getAllMembers(int page , int pageSize);
	public Member getMemberById(Long id);
	public void addMember(Member member);
	public void updateMember(Member member);
	public void deleteMember(Long id);
	int getTotalMembersCount(); 
}
