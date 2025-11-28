package com.circe_technology.mintic.etl_secop_contract.dto;


import com.circe_technology.mintic.etl_secop_contract.entity.ContratoSecop;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

/**
 * Data Transfer Object (DTO) used for deserializing the JSON response from the SECON API.
 * Uses @JsonProperty to handle non-standard keys like those containing colons or specific
 * underscores.
 * This DTO is then mapped to the ContratoSecop Entity.
 */
public record SecopContratoDto(
        // Socrata Internal Fields
        @JsonProperty(":id") String socrataId,
        @JsonProperty(":version") String socrataVersion,
        @JsonProperty(":created_at") Instant socrataCreatedAt,
        @JsonProperty(":updated_at") Instant socrataUpdatedAt,

        // Entity Information
        @JsonProperty("nombre_entidad") String nombreEntidad,
        @JsonProperty("nit_entidad") String nitEntidad,
        String departamento,
        String ciudad,
        String localizaci_n, // Note: Matches API key structure
        String orden,
        String sector,
        String rama,
        @JsonProperty("entidad_centralizada") String entidadCentralizada,
        // Maps to String, converted to Boolean in mapper

        // Contract Process Identifiers
        @JsonProperty("proceso_de_compra") String procesoDeCompra,
        @JsonProperty("id_contrato") String idContrato,
        @JsonProperty("referencia_del_contrato") String referenciaDelContrato,

        // Contract Status and Details
        @JsonProperty("estado_contrato") String estadoContrato,
        @JsonProperty("codigo_de_categoria_principal") String codigoDeCategoriaPrincipal,
        @JsonProperty("descripcion_del_proceso") String descripcionDelProceso,
        @JsonProperty("tipo_de_contrato") String tipoDeContrato,
        @JsonProperty("modalidad_de_contratacion") String modalidadDeContratacion,
        @JsonProperty("justificacion_modalidad_de") String justificacionModalidadDe,
        @JsonProperty("condiciones_de_entrega") String condicionesDeEntrega,

        // Provider Information
        @JsonProperty("tipodocproveedor") String tipodocproveedor,
        @JsonProperty("documento_proveedor") String documentoProveedor,
        @JsonProperty("proveedor_adjudicado") String proveedorAdjudicado,
        @JsonProperty("es_grupo") String esGrupo,
        @JsonProperty("es_pyme") String esPyme,

        // Payment and Execution Flags
        @JsonProperty("habilita_pago_adelantado") String habilitaPagoAdelantado,
        @JsonProperty("liquidaci_n") String liquidacionApi,
        // Renamed to avoid collision/clarify API field
        @JsonProperty("obligaci_n_ambiental") String obligacionAmbiental,
        @JsonProperty("obligaciones_postconsumo") String obligacionesPostconsumo,
        String reversion,
        @JsonProperty("origen_de_los_recursos") String origenDeLosRecursos,
        @JsonProperty("destino_gasto") String destinoGasto,

        // Financial Values (Stored as String in JSON, converted to BigDecimal in mapper)
        @JsonProperty("valor_del_contrato") String valorDelContrato,
        @JsonProperty("valor_de_pago_adelantado") String valorDePagoAdelantado,
        @JsonProperty("valor_facturado") String valorFacturado,
        @JsonProperty("valor_pendiente_de_pago") String valorPendienteDePago,
        @JsonProperty("valor_pagado") String valorPagado,
        @JsonProperty("valor_amortizado") String valorAmortizado,
        @JsonProperty("valor_pendiente_de_ejecucion") String valorPendienteDeEjecucion,
        @JsonProperty("saldo_cdp") String saldoCdp,
        @JsonProperty("saldo_vigencia") String saldoVigencia,
        @JsonProperty("valor_pendiente_de") String valorPendienteDe,

        // BPÍN and Post-Conflict Details
        @JsonProperty("estado_bpin") String estadoBpin,
        @JsonProperty("codigo_bpin") String codigoBpin,
        @JsonProperty("anno_bpin") String annoBpin,
        String espostconflicto,
        @JsonProperty("dias_adicionados") String diasAdicionados,
        // Stored as String, converted to Integer in mapper
        @JsonProperty("puntos_del_acuerdo") String puntosDelAcuerdo,
        @JsonProperty("pilares_del_acuerdo") String pilaresDelAcuerdo,

        // URLs (Nested Object - requires custom handling)
        @JsonProperty("urlproceso") UrlWrapper urlProcesoWrapper,

        // Other Identifiers and Details
        @JsonProperty("codigo_entidad") String codigoEntidad,
        @JsonProperty("codigo_proveedor") String codigoProveedor,
        @JsonProperty("objeto_del_contrato") String objetoDelContrato,
        @JsonProperty("duraci_n_del_contrato") String duracionDelContrato,
        @JsonProperty("nombre_del_banco") String nombreDelBanco,
        @JsonProperty("tipo_de_cuenta") String tipoDeCuenta,
        @JsonProperty("n_mero_de_cuenta") String numeroDeCuenta,

        // Representative/Personnel Information
        @JsonProperty("nombre_representante_legal") String nombreRepresentanteLegal,
        @JsonProperty("nacionalidad_representante_legal") String nacionalidadRepresentanteLegal,
        @JsonProperty("domicilio_representante_legal") String domicilioRepresentanteLegal,
        @JsonProperty("tipo_de_identificaci_n_representante_legal") String tipoDeIdentificacionRepresentanteLegal,
        @JsonProperty("identificaci_n_representante_legal") String identificacionRepresentanteLegal,
        @JsonProperty("g_nero_representante_legal") String generoRepresentanteLegal,

        @JsonProperty("nombre_ordenador_del_gasto") String nombreOrdenadorDelGasto,
        @JsonProperty("tipo_de_documento_ordenador_del_gasto") String tipoDeDocumentoOrdenadorDelGasto,
        @JsonProperty("n_mero_de_documento_ordenador_del_gasto") String numeroDeDocumentoOrdenadorDelGasto,

        @JsonProperty("nombre_supervisor") String nombreSupervisor,
        @JsonProperty("tipo_de_documento_supervisor") String tipoDeDocumentoSupervisor,
        @JsonProperty("n_mero_de_documento_supervisor") String numeroDeDocumentoSupervisor,

        @JsonProperty("nombre_ordenador_de_pago") String nombreOrdenadorDePago,
        @JsonProperty("tipo_de_documento_ordenador_de_pago") String tipoDeDocumentoOrdenadorDePago,
        @JsonProperty("n_mero_de_documento_ordenador_de_pago") String numeroDeDocumentoOrdenadorDePago,

        // Final Contract Metadata
        @JsonProperty("el_contrato_puede_ser_prorrogado") String elContratoPuedeSerProrrogado,
        @JsonProperty("documentos_tipo") String documentosTipo,
        @JsonProperty("descripcion_documentos_tipo") String descripcionDocumentosTipo

) {
    /** Helper class for nested URL object: {"url": "..."} */
    public record UrlWrapper(String url) {
    }

    /** Maps the DTO fields to the ContratoSecop Entity. */
    public ContratoSecop toEntity() {
        ContratoSecop entity = new ContratoSecop();

        // --- Socrata Internal Fields ---
        entity.setSocrataId(socrataId);
        entity.setSocrataVersion(socrataVersion);
        entity.setSocrataCreatedAt(socrataCreatedAt);
        entity.setSocrataUpdatedAt(socrataUpdatedAt);

        // --- Entity Information ---
        entity.setNombreEntidad(nombreEntidad);
        entity.setNitEntidad(nitEntidad);
        entity.setDepartamento(departamento);
        entity.setCiudad(ciudad);
        entity.setLocalizacion(localizaci_n);
        entity.setOrden(orden);
        entity.setSector(sector);
        entity.setRama(rama);
        entity.setEntidadCentralizada(safeToBoolean(entidadCentralizada));

        // --- Contract Process Identifiers ---
        entity.setProcesoDeCompra(procesoDeCompra);
        entity.setIdContrato(idContrato);
        entity.setReferenciaDelContrato(referenciaDelContrato);

        // --- Contract Status and Details ---
        entity.setEstadoContrato(estadoContrato);
        entity.setCodigoDeCategoriaPrincipal(codigoDeCategoriaPrincipal);
        entity.setDescripcionDelProceso(descripcionDelProceso);
        entity.setTipoDeContrato(tipoDeContrato);
        entity.setModalidadDeContratacion(modalidadDeContratacion);
        entity.setJustificacionModalidadDe(justificacionModalidadDe);
        entity.setCondicionesDeEntrega(condicionesDeEntrega);

        // --- Provider Information ---
        entity.setTipodocproveedor(tipodocproveedor);
        entity.setDocumentoProveedor(documentoProveedor);
        entity.setProveedorAdjudicado(proveedorAdjudicado);
        entity.setEsGrupo(safeToBoolean(esGrupo));
        entity.setEsPyme(safeToBoolean(esPyme));

        // --- Payment and Execution Flags ---
        entity.setHabilitaPagoAdelantado(safeToBoolean(habilitaPagoAdelantado));
        entity.setLiquidacion(safeToBoolean(liquidacionApi));
        entity.setObligacionAmbiental(safeToBoolean(obligacionAmbiental));
        entity.setObligacionesPostconsumo(safeToBoolean(obligacionesPostconsumo));
        entity.setReversion(safeToBoolean(reversion));
        entity.setOrigenDeLosRecursos(origenDeLosRecursos);
        entity.setDestinoGasto(destinoGasto);

        // --- Financial Values (Conversion) ---
        entity.setValorDelContrato(safeToDecimal(valorDelContrato));
        entity.setValorDePagoAdelantado(safeToDecimal(valorDePagoAdelantado));
        entity.setValorFacturado(safeToDecimal(valorFacturado));
        entity.setValorPendienteDePago(safeToDecimal(valorPendienteDePago));
        entity.setValorPagado(safeToDecimal(valorPagado));
        entity.setValorAmortizado(safeToDecimal(valorAmortizado));
        entity.setValorPendienteDeEjecucion(safeToDecimal(valorPendienteDeEjecucion));
        entity.setSaldoCdp(safeToDecimal(saldoCdp));
        entity.setSaldoVigencia(safeToDecimal(saldoVigencia));
        entity.setValorPendienteDe(safeToDecimal(valorPendienteDe));

        // --- BPÍN and Post-Conflict Details ---
        entity.setEstadoBpin(estadoBpin);
        entity.setCodigoBpin(codigoBpin);
        entity.setAnnoBpin(annoBpin);
        entity.setEspostconflicto(safeToBoolean(espostconflicto));
        entity.setDiasAdicionados(safeToInteger(diasAdicionados));
        entity.setPuntosDelAcuerdo(puntosDelAcuerdo);
        entity.setPilaresDelAcuerdo(pilaresDelAcuerdo);

        // --- URLs and other Identifiers ---
        entity.setUrlProceso(Optional.ofNullable(urlProcesoWrapper).map(UrlWrapper::url).orElse(
                null));
        entity.setCodigoEntidad(codigoEntidad);
        entity.setCodigoProveedor(codigoProveedor);
        entity.setObjetoDelContrato(objetoDelContrato);
        entity.setDuracionDelContrato(duracionDelContrato);
        entity.setNombreDelBanco(nombreDelBanco);
        entity.setTipoDeCuenta(tipoDeCuenta);
        entity.setNumeroDeCuenta(numeroDeCuenta);

        // --- Representative/Personnel Information (Handling non-standard API keys) ---
        entity.setNombreRepresentanteLegal(nombreRepresentanteLegal);
        entity.setNacionalidadRepresentanteLegal(nacionalidadRepresentanteLegal);
        entity.setDomicilioRepresentanteLegal(domicilioRepresentanteLegal);
        entity.setTipoDeIdentificacionRepresentanteLegal(tipoDeIdentificacionRepresentanteLegal);
        entity.setIdentificacionRepresentanteLegal(identificacionRepresentanteLegal);
        entity.setGeneroRepresentanteLegal(generoRepresentanteLegal);

        entity.setNombreOrdenadorDelGasto(nombreOrdenadorDelGasto);
        entity.setTipoDeDocumentoOrdenadorDelGasto(tipoDeDocumentoOrdenadorDelGasto);
        entity.setNumeroDeDocumentoOrdenadorDelGasto(numeroDeDocumentoOrdenadorDelGasto);

        entity.setNombreSupervisor(nombreSupervisor);
        entity.setTipoDeDocumentoSupervisor(tipoDeDocumentoSupervisor);
        entity.setNumeroDeDocumentoSupervisor(numeroDeDocumentoSupervisor);

        entity.setNombreOrdenadorDePago(nombreOrdenadorDePago);
        entity.setTipoDeDocumentoOrdenadorDePago(tipoDeDocumentoOrdenadorDePago);
        entity.setNumeroDeDocumentoOrdenadorDePago(numeroDeDocumentoOrdenadorDePago);

        // --- Final Contract Metadata ---
        entity.setElContratoPuedeSerProrrogado(safeToBoolean(elContratoPuedeSerProrrogado));
        entity.setDocumentosTipo(safeToBoolean(documentosTipo));
        entity.setDescripcionDocumentosTipo(descripcionDocumentosTipo);

        return entity;
    }

    private static Integer safeToInteger(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return 0;
        }
    }

    private static boolean safeToBoolean(String s) {
        // Utility to safely parse Strings (Si/No) to Boolean
        if (s == null) return false;
        return s.trim().equalsIgnoreCase("Si") || s.trim().equalsIgnoreCase("Centralizada");
    }

    private static BigDecimal safeToDecimal(String s) {
        try {
            return new BigDecimal(s);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }
}