package mx.com.isc.itsc.hp_abner.pimacit_app;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jrrg_94 on 16/11/16.
 */

public class Negocios {
    @JsonProperty("Id")
    public long Id;
    @JsonProperty("Nombre")
    public String Nombre;
    @JsonProperty("Tipo")
    public int Tipo;
    @JsonProperty("IdCd")
    public int IdCd;
    @JsonProperty("Direccion")
    public String Direccion;
    @JsonProperty("Tel")
    public String Tel;
    @JsonProperty("TiposCita")
    public String TiposCita;
    @JsonProperty("Horarios")
    public String Horarios;
}
