package com.circe_technology.mintic.etl_secop_contract.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Entity representing a contract record from the 'contratos_secop' PostgreSQL table.
 * Uses standard JPA annotations for persistence and Lombok for data handling.
 */
@Entity
@Table(name = "contratos_secop_v2")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContratoSecop {

    // --- Internal Socrata/JSON fields ---

    /** The Socrata unique identifier, serving as the primary key. */
    @Id
    @Column(name = "socrata_id", length = 200)
    private String socrataId;

    @Column(name = "socrata_version")
    private String socrataVersion;

    @Column(name = "socrata_created_at")
    private Instant socrataCreatedAt;

    @Column(name = "socrata_updated_at")
    private Instant socrataUpdatedAt;

    // --- Entity Information ---

    /** Name of the contracting entity (NOT NULL). */
    @Column(name = "nombre_entidad", nullable = false)
    private String nombreEntidad;

    @Column(name = "nit_entidad", length = 15)
    private String nitEntidad;

    private String departamento;
    private String ciudad;
    private String localizacion;
    private String orden;
    private String sector;
    private String rama;

    @Column(name = "entidad_centralizada")
    private Boolean entidadCentralizada;

    // --- Contract Process Identifiers ---

    @Column(name = "proceso_de_compra", length = 20)
    private String procesoDeCompra;

    /** Unique contract identifier (NOT NULL). */
    @Column(name = "id_contrato", length = 20, nullable = false)
    private String idContrato;

    @Column(name = "referencia_del_contrato", length = 20)
    private String referenciaDelContrato;

    // --- Contract Status and Details ---

    @Column(name = "estado_contrato")
    private String estadoContrato;

    @Column(name = "codigo_de_categoria_principal", length = 20)
    private String codigoDeCategoriaPrincipal;

    @Column(name = "descripcion_del_proceso")
    private String descripcionDelProceso;

    @Column(name = "tipo_de_contrato")
    private String tipoDeContrato;

    @Column(name = "modalidad_de_contratacion")
    private String modalidadDeContratacion;

    @Column(name = "justificacion_modalidad_de")
    private String justificacionModalidadDe;

    @Column(name = "condiciones_de_entrega")
    private String condicionesDeEntrega;

    // --- Provider Information ---

    @Column(name = "tipodocproveedor", length = 5)
    private String tipodocproveedor;

    @Column(name = "documento_proveedor", length = 15)
    private String documentoProveedor;

    @Column(name = "proveedor_adjudicado")
    private String proveedorAdjudicado;

    @Column(name = "es_grupo")
    private Boolean esGrupo;

    @Column(name = "es_pyme")
    private Boolean esPyme;

    // --- Payment and Execution Flags ---

    @Column(name = "habilita_pago_adelantado")
    private Boolean habilitaPagoAdelantado;

    /** Note: Column name uses 'liquidaci_n' as per the SQL script to match the database column. */
    @Column(name = "liquidaci_n")
    private Boolean liquidacion;

    @Column(name = "obligaci_n_ambiental")
    private Boolean obligacionAmbiental;

    @Column(name = "obligaciones_postconsumo")
    private Boolean obligacionesPostconsumo;

    private Boolean reversion;

    @Column(name = "origen_de_los_recursos")
    private String origenDeLosRecursos;

    @Column(name = "destino_gasto")
    private String destinoGasto;

    // --- Financial Values (BigDecimal for precision) ---

    @Column(name = "valor_del_contrato", precision = 18, scale = 2)
    private BigDecimal valorDelContrato;

    @Column(name = "valor_de_pago_adelantado", precision = 18, scale = 2)
    private BigDecimal valorDePagoAdelantado;

    @Column(name = "valor_facturado", precision = 18, scale = 2)
    private BigDecimal valorFacturado;

    @Column(name = "valor_pendiente_de_pago", precision = 18, scale = 2)
    private BigDecimal valorPendienteDePago;

    @Column(name = "valor_pagado", precision = 18, scale = 2)
    private BigDecimal valorPagado;

    @Column(name = "valor_amortizado", precision = 18, scale = 2)
    private BigDecimal valorAmortizado;

    @Column(name = "valor_pendiente_de_ejecucion", precision = 18, scale = 2)
    private BigDecimal valorPendienteDeEjecucion;

    @Column(name = "saldo_cdp", precision = 18, scale = 2)
    private BigDecimal saldoCdp;

    @Column(name = "saldo_vigencia", precision = 18, scale = 2)
    private BigDecimal saldoVigencia;

    // --- BPÍN and Post-Conflict Details ---

    @Column(name = "estado_bpin")
    private String estadoBpin;

    @Column(name = "codigo_bpin", length = 20)
    private String codigoBpin;

    @Column(name = "anno_bpin", length = 5)
    private String annoBpin;

    private Boolean espostconflicto;

    @Column(name = "dias_adicionados")
    private Integer diasAdicionados;

    @Column(name = "puntos_del_acuerdo")
    private String puntosDelAcuerdo;

    @Column(name = "pilares_del_acuerdo")
    private String pilaresDelAcuerdo;

    // --- URLs and other Identifiers ---

    @Column(name = "url_proceso")
    private String urlProceso;

    @Column(name = "codigo_entidad", length = 20)
    private String codigoEntidad;

    @Column(name = "codigo_proveedor", length = 20)
    private String codigoProveedor;

    @Column(name = "objeto_del_contrato")
    private String objetoDelContrato;

    @Column(name = "duraci_n_del_contrato")
    private String duracionDelContrato;

    // --- Banking Details ---

    @Column(name = "nombre_del_banco")
    private String nombreDelBanco;

    @Column(name = "tipo_de_cuenta")
    private String tipoDeCuenta;

    @Column(name = "numero_de_cuenta", length = 30)
    private String numeroDeCuenta;

    // --- Representative/Personnel Information ---

    @Column(name = "nombre_representante_legal")
    private String nombreRepresentanteLegal;

    @Column(name = "nacionalidad_representante_legal", length = 5)
    private String nacionalidadRepresentanteLegal;

    @Column(name = "domicilio_representante_legal")
    private String domicilioRepresentanteLegal;

    @Column(name = "tipo_de_identificacion_representante_legal")
    private String tipoDeIdentificacionRepresentanteLegal;

    @Column(name = "identificacion_representante_legal", length = 20)
    private String identificacionRepresentanteLegal;

    @Column(name = "genero_representante_legal")
    private String generoRepresentanteLegal;

    @Column(name = "nombre_ordenador_del_gasto")
    private String nombreOrdenadorDelGasto;

    @Column(name = "tipo_de_documento_ordenador_del_gasto")
    private String tipoDeDocumentoOrdenadorDelGasto;

    @Column(name = "numero_de_documento_ordenador_del_gasto", length = 20)
    private String numeroDeDocumentoOrdenadorDelGasto;

    @Column(name = "nombre_supervisor")
    private String nombreSupervisor;

    @Column(name = "tipo_de_documento_supervisor")
    private String tipoDeDocumentoSupervisor;

    @Column(name = "numero_de_documento_supervisor", length = 20)
    private String numeroDeDocumentoSupervisor;

    @Column(name = "nombre_ordenador_de_pago")
    private String nombreOrdenadorDePago;

    @Column(name = "tipo_de_documento_ordenador_de_pago")
    private String tipoDeDocumentoOrdenadorDePago;

    @Column(name = "numero_de_documento_ordenador_de_pago", length = 20)
    private String numeroDeDocumentoOrdenadorDePago;

    // --- Final Contract Metadata ---

    @Column(name = "el_contrato_puede_ser_prorrogado")
    private Boolean elContratoPuedeSerProrrogado;

    @Column(name = "documentos_tipo")
    private Boolean documentosTipo;

    @Column(name = "descripcion_documentos_tipo")
    private String descripcionDocumentosTipo;

    /** Additional financial value. */
    @Column(name = "valor_pendiente_de", precision = 18, scale = 2)
    private BigDecimal valorPendienteDe;
}