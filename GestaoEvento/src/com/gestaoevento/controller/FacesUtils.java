package com.gestaoevento.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class FacesUtils implements Serializable{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public String[] array(String campos, String separador) {
        	return campos.split(separador);
        }
        
        public String stringParam(String valor) {
                return valor;
        }

        public boolean isString(Object object) {
                return object instanceof String; 
        }

        public boolean isDate(Object object) {
                return object instanceof java.util.Date 
                                || object instanceof java.sql.Date 
                                || object instanceof java.sql.Timestamp
                                || object instanceof java.sql.Time;
        }

        public String getClass(Object object) {
                return object.getClass().getName();
        }
}