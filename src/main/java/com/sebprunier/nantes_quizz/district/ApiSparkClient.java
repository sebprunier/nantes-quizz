package com.sebprunier.nantes_quizz.district;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;

import java.util.List;

// https://opendatanantes.apispark.net/v1/lieux_dits_nantes_metropole?ville=NANTES&$size=500
public interface ApiSparkClient {

    @Headers({
            "Accept: application/json"
    })
    @GET("/v1/lieux_dits_nantes_metropole?ville=NANTES&$size=500")
    public Call<List<DistrictInfo>> getNantesDistricts();

}
