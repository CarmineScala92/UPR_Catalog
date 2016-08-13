/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.commons;

public abstract class AbstractManager<T> {

	protected T imp;

	public abstract T getManagerImplementor(String pManagerType);
}
