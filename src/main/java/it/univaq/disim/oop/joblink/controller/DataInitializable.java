/**
 * Interfaccia per l'inizializzazione dei dati
 */
package it.univaq.disim.oop.joblink.controller;

public interface DataInitializable<T> {
	
	default void initializeData(T t) {
		
	}
}
