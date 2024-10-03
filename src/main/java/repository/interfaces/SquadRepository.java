package repository.interfaces;
import java.util.List;

import model.Squad; 

public interface SquadRepository {
	
	List<Squad> getAllSquads(int page ,int pageSize) ;
	Squad getSquadById(long id);
	void addSquad(Squad squad);
	void updateSquad(Squad sqaud);
	void deleteSquad(long id);
	 

}
