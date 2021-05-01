package it.polito.tdp.poweroutages.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	int best;
	List<PowerOutage> wc;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<PowerOutage> worstCase(Nerc nerc,int maxanni,int maxore){
		List<PowerOutage> tutteOutagesNerc=podao.getPowerOutagesbyNerc(nerc.getId());
		wc = new LinkedList<PowerOutage>();
		best=0;
		for(int i=2000;i<2015;i++) {
			List<PowerOutage> outages=new LinkedList<PowerOutage>();
			for(PowerOutage p:tutteOutagesNerc) {
				if(p.date_event_began.getYear()<i+maxanni && p.date_event_began.getYear()>=i) {
					outages.add(p);
				}
			}
			List<PowerOutage> parziale=new LinkedList<PowerOutage>();
			cerca(outages,parziale,maxore,0);
		}
		return wc;
	}
	
	public void cerca(List<PowerOutage> outages,List<PowerOutage> parziale,int maxore,int livello) {
		if(outages.size()==0) {
			return;
		}
		if(terminazione(outages,parziale,maxore)) {
			if(calcolaPersone(parziale)>best) {
				best=calcolaPersone(parziale);
				wc = new LinkedList<PowerOutage>();
				wc.addAll(parziale);
			}
			return;
		}
		for(PowerOutage p:outages) {
			if(!parziale.contains(p)) {
				if(outagesValido(parziale,p,maxore)) {
					parziale.add(p);
					cerca(outages,parziale,maxore,livello+1);
					parziale.remove(p);
				}
			}
		}
		return;
	}
	
	public int contaOre(List<PowerOutage> outages) {
		int ore=0;
		for(PowerOutage p:outages) {
			ore=ore+p.getOreOutage();
		}
		return ore;
	}
	
	public boolean outagesValido(List<PowerOutage> outages,PowerOutage p,int maxore) {
		int oretotali=contaOre(outages)+p.getOreOutage();
		if(oretotali<=maxore) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean terminazione(List<PowerOutage> outages,List<PowerOutage> parziale,int maxore) {
		List<PowerOutage> complementoparziale=new LinkedList<PowerOutage>();
		for(PowerOutage p:outages) {
			if(!parziale.contains(p)) {
				complementoparziale.add(p);
			}
		}
		if(complementoparziale.size()==0) {
			return true;
		}
		int oreMinime=complementoparziale.get(0).getOreOutage();
		for(PowerOutage p:complementoparziale) {
			if(p.getOreOutage()<oreMinime) {
				oreMinime=p.getOreOutage();
			}
		}
		if(contaOre(parziale)+oreMinime>maxore) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int calcolaPersone(List<PowerOutage> outages) {
		int persone=0;
		for(PowerOutage p:outages) {
			persone=persone+p.getCustomers_affected();
		}
		return persone;
	}

}
