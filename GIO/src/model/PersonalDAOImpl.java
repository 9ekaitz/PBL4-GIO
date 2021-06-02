package model;

import java.util.ArrayList;
import java.util.List;

public class PersonalDAOImpl implements PersonalDAO{
	
	List<Trabajador>listaPersonal;
	
	public PersonalDAOImpl() {
		this.listaPersonal = new ArrayList<>();
		inicializar();
	}

	@Override
	public void add(Trabajador trabajador) {
		listaPersonal.add(trabajador);		
	}

	@Override
	public Trabajador[] getTrabajadores() {
		return listaPersonal.toArray(new Trabajador[0]);
	}
	
	public void inicializar() {
		listaPersonal.add(new Trabajador("Hola","que tal","75648514M","estibador"));
		listaPersonal.add(new Trabajador("Que tal","hola","75778514L","jefe"));
	}
	
}
