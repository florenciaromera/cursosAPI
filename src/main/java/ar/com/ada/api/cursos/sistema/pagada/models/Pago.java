package ar.com.ada.api.cursos.sistema.pagada.models;

import java.math.BigDecimal;
import java.util.Date;

public class Pago {
    /*{
        "importePagado": 11,
        "fechaPago": "2020-08-05",
        "medioPago":"TARJETA",
        "infoMedioPago": "3939393939",
        "moneda": "ARS"
    }*/

    public BigDecimal importePagado;
    public Date fechaPago;
    public String medioPago;
    public String infoMedioPago;
    public String moneda;
}
