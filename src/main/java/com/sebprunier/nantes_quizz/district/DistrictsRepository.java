package com.sebprunier.nantes_quizz.district;

import retrofit.*;

import javax.inject.Singleton;
import java.util.*;

@Singleton
public class DistrictsRepository {

    private List<String> DISTRICTS_INFOS_IDS = new ArrayList<>();
    private Map<String, DistrictInfo> DISTRICTS_INFOS = new HashMap<>();

    public DistrictsRepository() throws Exception {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://opendatanantes.apispark.net")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        ApiSparkClient apiSparkClient = retrofit.create(ApiSparkClient.class);

        Call<List<DistrictInfo>> call = apiSparkClient.getNantesDistricts();

        Response<List<DistrictInfo>> response = call.execute();

        for (DistrictInfo districtInfo : response.body()) {
            DISTRICTS_INFOS_IDS.add(districtInfo.id);
            DISTRICTS_INFOS.put(districtInfo.id, districtInfo);
        }

    }

    public static void main(String[] args) throws Exception {
        new DistrictsRepository();
    }

    public List<DistrictInfo> random3() {
        List<String> randomIds = new ArrayList<>();
        randomIds.addAll(DISTRICTS_INFOS_IDS);
        Collections.shuffle(randomIds);
        return Arrays.asList(
                DISTRICTS_INFOS.get(randomIds.get(0)),
                DISTRICTS_INFOS.get(randomIds.get(1)),
                DISTRICTS_INFOS.get(randomIds.get(2))
        );
    }

    public DistrictInfo getById(String id) {
        return DISTRICTS_INFOS.get(id);
    }

}
