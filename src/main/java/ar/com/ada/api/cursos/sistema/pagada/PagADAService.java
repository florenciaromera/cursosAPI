package ar.com.ada.api.cursos.sistema.pagada;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ar.com.ada.api.cursos.entities.Estudiante;
import ar.com.ada.api.cursos.entities.Inscripcion;
import ar.com.ada.api.cursos.sistema.pagada.models.*;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

@Service
public class PagADAService {

    @Value("${pagADA.apiKey}")
    private String apiKey;
    @Value("${pagADA.apiBaseUri}")
    public String apiBaseUri;
    @Value("${pagADA.empresaId}")
    public Integer empresaId;
    @Value("${pagADA.tipoServicioId}")
    public Integer tipoServicioId;

    public Integer crearDeudor(Estudiante estudiante) {

        Deudor deudor = new Deudor();
        //Mapear los datos del estudiante en el deudor
        deudor.nombre = estudiante.getNombre();
        deudor.idImpositivo = estudiante.getDocumento();
        deudor.tipoIdImpositivo = estudiante.getTipoDocumentoId().toString(); // No necesariamente este coincide con el de la API
        deudor.paisId = estudiante.getPaisId();
        deudor.tipoIdImpositivo = estudiante.getTipoDocumentoId().toString(); // No necesariamente este coincide con el de la API
        deudor.paisId = estudiante.getPaisId();
        deudor.tipoIdImpositivo = estudiante.getTipoDocumentoId().toString(); // No necesariamente este coincide con el de la API
        deudor.paisId = estudiante.getPaisId();
        deudor.tipoIdImpositivo = estudiante.getTipoDocumentoId().toString(); // No necesariamente este coincide con el de la API
        deudor.paisId = estudiante.getPaisId();
        deudor.tipoIdImpositivo = estudiante.getTipoDocumentoId().toString(); // No necesariamente este coincide con el de la API
        deudor.paisId = estudiante.getPaisId();

        ResultadoCreacionDeudor rd = crearDeudor(deudor);

        if (rd.isOk)
            return rd.id;

        return 0;
    }

    public ResultadoCreacionDeudor crearDeudor(Deudor deudor) {
        ResultadoCreacionDeudor resultado = new ResultadoCreacionDeudor();

        JsonNode r;
        HttpResponse<JsonNode> request = Unirest.post(this.apiBaseUri + "/deudores")
                .header("content-type", "application/json").body(deudor) //AcaPasamos el RequestBody
                .header("api", this.apiKey).asJson();

        r = request.getBody();

        JSONObject jsonObject = r.getObject();

        resultado.isOk = jsonObject.getBoolean("isOk");
        resultado.id = jsonObject.getInt("id");
        resultado.message = jsonObject.getString("message");

        return resultado;

    }

    public Integer crearDeuda(Inscripcion inscripcion) {
        Servicio servicio = new Servicio();

        servicio.empresaId = this.empresaId; //ID de empresa en PagADA NO TIENE QUE ESTAR HARCODEADO
        servicio.tipoServicioId = this.tipoServicioId; //EDUCACION
        servicio.deudorId = inscripcion.getUsuario().getEstudiante().getPagADA_deudorId();
        servicio.tipoComprobanteId = "FACTURA"; //SERVICIO NO ANDA
        //Armo una combinacion de estudiante y curso
        servicio.numero = "E" + inscripcion.getUsuario().getEstudiante().getEstudianteId() + "C"
                + inscripcion.getCurso().getCursoId();
        servicio.fechaEmision = inscripcion.getFecha();
        servicio.fechaVencimiento = servicio.fechaEmision;
        servicio.importe = inscripcion.getCurso().getPrecio();
        servicio.moneda = inscripcion.getCurso().getMoneda();//Aca justo coincide
        servicio.codigoBarras = "CURSOPINTURILLO" + servicio.numero; //Esta combinacion es unica en todo PAGADA
        servicio.codigoBarras += (new Date()).toString();
        servicio.estadoId = "PENDIENTE";

        ResultadoCreacionDeuda rd = crearDeuda(servicio);

        inscripcion.setPagADA_CodigoBarras(servicio.codigoBarras);
        inscripcion.setPagADA_importe(servicio.importe);
        inscripcion.setPagADA_moneda(servicio.moneda);
        inscripcion.setPagADA_ServicioId(rd.id);

        if (rd.isOk)
            return rd.id;

        return 0;
    }

    public ResultadoCreacionDeuda crearDeuda(Servicio servicio) {
        ResultadoCreacionDeuda resultado = new ResultadoCreacionDeuda();

        //Transformo el servicio, como String en formato JSON
        String infoServicioStr = JSONObject.valueToString(servicio);

        //Levanto el string y lo leo en un objeto JSON
        JSONObject infoServicioJson = new JSONObject(infoServicioStr);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //MODIFICO el nodo "fechaPago" a formato YYYY-MM-DD
        infoServicioJson.put("fechaEmision", formatter.format(servicio.fechaEmision));
        infoServicioJson.put("fechaVencimiento", formatter.format(servicio.fechaVencimiento));
        
        JsonNode r;
        HttpResponse<JsonNode> request = Unirest.post(this.apiBaseUri + "/servicios")
                .header("content-type", "application/json").body(infoServicioJson) //AcaPasamos el RequestBody
                .header("api", this.apiKey).asJson(); //QUE TAMPOCO HAY QUE HAARCODEARLA

        r = request.getBody();

        JSONObject jsonObject = r.getObject();

        resultado.isOk = jsonObject.getBoolean("isOk");
        resultado.id = jsonObject.getInt("id");
        resultado.message = jsonObject.getString("message");

        return resultado;

    }

    public Integer pagarServicio(Inscripcion inscripcion, String medioPago, String infoMedioPago) {

        Pago pago = new Pago();
        //Mapear los datos de la inscripcion al pago

        pago.importePagado = inscripcion.getPagADA_importe();
        pago.fechaPago = new Date();
        pago.infoMedioPago = infoMedioPago; //Aca tenemos que poner el dato de tarjeta y no tiene que estar gardada en ningun lado.
        pago.medioPago = medioPago; //Tarje
        ResultadoPago rd = pagarServicio(inscripcion.getPagADA_ServicioId(), pago);

        if (rd.isOk) {
            inscripcion.setPagADA_PagoId(rd.id);
            return rd.id;
        }

        return 0;
    }

    public ResultadoPago pagarServicio(Integer servicioId, Pago pago) {
        ResultadoPago resultado = new ResultadoPago();

        JsonNode r;

        //Transformo el pago, como String en formato JSON
        String infoPagoStr = JSONObject.valueToString(pago);

        //Levanto el string y lo leo en un objeto JSON
        JSONObject infoPagoJson = new JSONObject(infoPagoStr);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //MODIFICO el nodo "fechaPago" a formato YYYY-MM-DD
        infoPagoJson.put("fechaPago", formatter.format(pago.fechaPago));

        HttpResponse<JsonNode> request = Unirest.post(this.apiBaseUri + "/servicios/{id}")
                .routeParam("id", servicioId.toString()).header("content-type", "application/json")
                .body(infoPagoJson) //AcaPasamos el RequestBody
                //una fecha la transforma asi: Tuesday 29 of September 2020
                //lo tiene que mandar en YYYY-MM-DD
                .header("api", this.apiKey).asJson(); //QUE TAMPOCO HAY QUE HAARCODEARLA

        r = request.getBody();

        JSONObject jsonObject = r.getObject();

        resultado.isOk = jsonObject.getBoolean("isOk");
        resultado.id = jsonObject.getInt("id");
        resultado.message = jsonObject.getString("message");
        resultado.nombreEmpresa = jsonObject.getString("nombreEmpresa");

        return resultado;

    }

}
