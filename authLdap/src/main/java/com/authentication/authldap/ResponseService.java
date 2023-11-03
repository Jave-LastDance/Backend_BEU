package com.authentication.authldap;

import java.util.ArrayList;
import java.util.Collection;

public class ResponseService {
        private String nombre;
        private String apellidos;
        private String emplId;
        private String tipoDocumento;
        private String documento;
        private String oprId;
        private String diasVencimiento;
        private String programa;
        private String dependencia;
        private Collection<String> roles;
        private Collection<String> grupos;

        public String getTipoDocumento() {
            return tipoDocumento == null ? "" : tipoDocumento;
        }

        public void setTipoDocumento(String tipoDocumento) {
            this.tipoDocumento = tipoDocumento;
        }

        public String getPrograma() {
            return programa == null ? "" : programa;
        }

        public void setPrograma(String programa) {
            this.programa = programa;
        }

        public String getDependencia() {
            return dependencia == null ? "" : dependencia;
        }

        public void setDependencia(String dependencia) {
            this.dependencia = dependencia;
        }

        public Collection<String> getRoles() {
            if(roles == null)
                roles = new ArrayList<String>();

            return roles;
        }

        public void setRoles(Collection<String> roles) {
            this.roles = roles;
        }

        public Collection<String> getGrupos() {

            if(grupos == null)
                grupos = new ArrayList<String>();

            return grupos;
        }

        public void setGrupos(Collection<String> grupos) {
            this.grupos = grupos;
        }

        public String getNombre() {
            return nombre == null ? "" : nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellidos() {
            return apellidos == null ? "" : apellidos;
        }

        public void setApellidos(String apellidos) {
            this.apellidos = apellidos;
        }

        public String getEmplId() {
            return emplId == null ? "" : emplId;
        }

        public void setEmplId(String emplId) {
            this.emplId = emplId;
        }

        public String getDocumento() {
            return documento == null ? "" : documento;
        }

        public void setDocumento(String documento) {
            this.documento = documento;
        }

        public String getOprId() {
            return oprId == null ? "" : oprId;
        }

        public void setOprId(String oprId) {
            this.oprId = oprId;
        }

        public String getDiasVencimiento() {
            return diasVencimiento == null ? "" : diasVencimiento;
        }

        public void setDiasVencimiento(String fechaVencimiento) {
            this.diasVencimiento = fechaVencimiento;
        }
 }

