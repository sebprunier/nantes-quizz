package com.sebprunier.nantes_quizz.district;

import net.codestory.http.annotations.Get;
import net.codestory.http.annotations.Post;
import net.codestory.http.annotations.Prefix;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Prefix("api/quizz/district")
public class DistrictQuizzEndpoint {

    private DistrictsRepository districtsRepository;

    @Inject
    public DistrictQuizzEndpoint(DistrictsRepository districtsRepository) {
        this.districtsRepository = districtsRepository;
    }

    @Get("new")
    public DistrictQuizz newDistrictQuizz() {
        List<DistrictInfo> districtInfos = districtsRepository.random3();

        DistrictInfo districtInfo = districtInfos.get(0);
        String districtInfoLat = districtInfo.lat_WGS84;
        String districtInfoLon = districtInfo.long_WGS84;

        Collections.shuffle(districtInfos);

        return new DistrictQuizz(
                districtInfoLat,
                districtInfoLon,
                districtInfos.get(0).id,
                districtInfos.get(0).nom,
                districtInfos.get(1).id,
                districtInfos.get(1).nom,
                districtInfos.get(2).id,
                districtInfos.get(2).nom
        );
    }

    @Post("check")
    public DistrictQuizzResult checkDistrictQuizz(DistrictQuizzProposal districtQuizzProposal) {
        DistrictInfo districtInfo = districtsRepository.getById(districtQuizzProposal.getProposal());

        DistrictQuizzResult result = new DistrictQuizzResult();
        result.setResult(
                districtInfo.lat_WGS84.equals(districtQuizzProposal.getDistrictInfoLat())
                        && districtInfo.long_WGS84.equals(districtQuizzProposal.getDistrictInfoLon())
        );
        //result.setCorrectOption(photoInfo.getDetails());

        return result;
    }
}
