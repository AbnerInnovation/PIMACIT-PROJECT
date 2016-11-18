package mx.com.isc.itsc.hp_abner.pimacit_app;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jrrg_94 on 17/11/16.
 */

public class JsonTiposCita {

    @JsonProperty("getTiposCitaResult")
    List<String> TiposCitaResult;


    public void setTipoCita(List<String> n)
    {
        this.TiposCitaResult=n;

    }


    public List<String> getTiposCitaResultResult()
    {
        return this.TiposCitaResult;
    }}
