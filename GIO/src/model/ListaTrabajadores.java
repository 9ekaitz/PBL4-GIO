package model;

import java.util.ArrayList;
import java.util.List;

public class ListaTrabajadores {
	
	List<Trabajador>listaPersonal;
	
	public ListaTrabajadores() {
		this.listaPersonal = new ArrayList<>();
		inicializar();
	}

	public void add(Trabajador trabajador) {
		listaPersonal.add(trabajador);
	}
	
	public void inicializar() {
		listaPersonal.add(new Trabajador("Hola","que tal","75648514M","estibador"));
		listaPersonal.add(new Trabajador("Que tal","hola","75778514L","jefe"));
	}
	
	public Trabajador[] getTrabajadores() {
		return listaPersonal.toArray(new Trabajador[0]);
	}

}