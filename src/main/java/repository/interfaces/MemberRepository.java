package repository.interfaces;

 
import java.util.List;

import model.Member;

public interface MemberRepository {
	
	List<Member> getAllMembers(int page , int pageSize);
	Member getMemberById(Long id);
	void addMember(Member member);
	void updateMember(Member member);
	void deleteMember(Long id);

}
