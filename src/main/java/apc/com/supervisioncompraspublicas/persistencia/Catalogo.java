package apc.com.supervisioncompraspublicas.persistencia;

import java.sql.Date;

public class Catalogo {
	
	private String contrato;
	private String descripcion;
	private String software;
	private String relacion;
	private Double precioRefencial;
	private String tipo;
	private String licencia;
	private String entidad;
	private Date fechaCompra;
	private String lugar;
	
	public Catalogo(String contrato, String descripcion, String software, String relacion, Double precioRefencial,
			String tipo, String licencia, String entidad, Date fechaCompra, String lugar) {
		this.contrato = contrato;
		this.descripcion = descripcion;
		this.software = software;
		this.relacion = relacion;
		this.precioRefencial = precioRefencial;
		this.tipo = tipo;
		this.licencia = licencia;
		this.entidad = entidad;
		this.fechaCompra = fechaCompra;
		this.lugar = lugar;
	}

	public String getContrato() {
		return contrato;
	}
	
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getSoftware() {
		return software;
	}
	
	public void setSoftware(String software) {
		this.software = software;
	}
	
	public String getRelacion() {
		return relacion;
	}
	
	public void setRelacion(String relacion) {
		this.relacion = relacion;
	}
	
	public Double getPrecioRefencial() {
		return precioRefencial;
	}
	
	public void setPrecioRefencial(Double precioRefencial) {
		this.precioRefencial = precioRefencial;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getLicencia() {
		return licencia;
	}
	
	public void setLicencia(String licencia) {
		this.licencia = licencia;
	}
	
	public String getEntidad() {
		return entidad;
	}
	
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	
	public Date getFechaCompra() {
		return fechaCompra;
	}
	
	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	
	public String getLugar() {
		return lugar;
	}
	
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
}
