package it.univaq.disim.oop.joblink.business.impl.ram;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.OffertaService;
import it.univaq.disim.oop.joblink.business.UtenteService;
import it.univaq.disim.oop.joblink.domain.Azienda;
import it.univaq.disim.oop.joblink.domain.Offerta;
import it.univaq.disim.oop.joblink.domain.StatoOfferta;

public class RAMOffertaServiceImpl implements OffertaService {
	private static List<Offerta> offerteAggiunte = new ArrayList<>();
	private static int idCounter = 1;
	
	private UtenteService utenteService;
	
	public RAMOffertaServiceImpl() {
		this.utenteService = new RAMUtenteServiceImpl();
	}

	@Override
	public List<Offerta> findAllOfferte(Azienda azienda) throws BusinessException {
		List<Offerta> result = new ArrayList<>();
		int id=1;
		Offerta tecnico = new Offerta();
		tecnico.setId(id++);
		tecnico.setTitoloOfferta("Tecnico operatore");
		tecnico.setAzienda(azienda);
		tecnico.setTestoOfferta("Si cerca tecnico operatore da inserire in turno. Richiesto diploma.");
		tecnico.setLocalita("Fucino");
		tecnico.setDataCreazione(2020, 06, 05);
		tecnico.setStato(StatoOfferta.ATTIVA);
		result.add(tecnico);
		return result;
		
	}

	@Override
	public void createOfferta(Offerta offerta) throws BusinessException {
		offerta.setId(idCounter++);
		offerteAggiunte.add(offerta);
		
	}

	@Override
	public void updateOfferta(Offerta offerta) throws BusinessException {
		for(Offerta off : offerteAggiunte) {
			if(offerta.getId() == off.getId()) {
				off.setTitoloOfferta(offerta.getTitoloOfferta());
				off.setTestoOfferta(offerta.getTestoOfferta());
				off.setLocalita(offerta.getLocalita());
				off.setAzienda(offerta.getAzienda());
				off.setDataCreazione(offerta.getDataCreazione().YEAR, offerta.getDataCreazione().MONTH, offerta.getDataCreazione().DAY_OF_MONTH);
			}
		}
		
	}

	@Override
	public Offerta findOffertaById(int id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
