package com.authentication.authldap;


public class ResponseServiceBus {

    private boolean validacion;
    private ResponseService persona;

    public boolean isValidacion() {
        return validacion;
    }

    public void setValidacion(boolean validacion) {
        this.validacion = validacion;
    }

    public ResponseService getPersona() {
        if (persona == null)
            persona = new ResponseService();
        return persona;
    }

    public void setPersona(ResponseService persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "ResponseServiceBus{" + "validacion=" + validacion + ", persona=" + persona + '}';
    }

}
