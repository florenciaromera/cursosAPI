package ar.com.ada.api.cursos.sistema.pagada.models;

import java.math.BigDecimal;
import java.util.Date;

public class Servicio {
    /*
    {
    "empresaId": 2,
    "deudorId": 1,
    "tipoServicioId": 12,
    "tipoComprobanteId": "SERVICIO",
    "numero": "939393981",
    "fechaEmision": "2020-05-08",
    "fechaVencimiento": "2020-06-08",
    "importe": 11,
    "moneda":"ARS",
    "codigoBarras": "12345681",
    "estadoId": "PENDIENTE"
}
    */
   
    public Integer empresaId;
    public Integer deudorId;
    public Integer tipoServicioId;
    public String tipoComprobanteId; //"SERVICIO" o "FACTURA"
    public String numero;
    public Date fechaEmision;
    public Date fechaVencimiento;
    public BigDecimal importe;
    public String moneda;
    public String codigoBarras; //En nuestro caso es unico en TODO Pagada, por lo cual vamos a agregarle la palabra "CURSOPINTURILLO"
    public String estadoId;
    
}
