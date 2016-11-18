package mx.com.isc.itsc.hp_abner.pimacit_app;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jrrg_94 on 16/11/16.
 */

public class JsonNegocios {
    @JsonProperty("getNegociosResult")
    List<Negocios> ResultNegocios;


    public void setNegocios(List<Negocios> n)
    {
        this.ResultNegocios=n;

    }


    public List<Negocios> getNegociosResult()
    {
        return this.ResultNegocios;
    }
}
