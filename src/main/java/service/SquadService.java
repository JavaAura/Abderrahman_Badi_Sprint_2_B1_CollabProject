package service;

import java.util.List;
import model.Squad;
import repository.interfaces.SquadRepository;
import repository.implementation.SquadRepositoryImpl;

public class SquadService {

	private SquadRepository squadRepository ;
	
	public SquadService() {
		this.squadRepository = new SquadRepositoryImpl();
	}
	
	public List<Squad> getAllSquads(int page , int pageSize){
		return squadRepository.getAllSquads(page, pageSize);
	}
	
	public Squad getSquadById(long id) {
		return squadRepository.getSquadById(id);
	}
	
	public void addSquad(Squad squad) {
		  squadRepository.addSquad(squad);
	}
	
	public void updateSquad(Squad squad) {
		 squadRepository.updateSquad(squad);
	}
	
	public void deleteSquad(long id) {
		squadRepository.deleteSquad(id);
	}
}
