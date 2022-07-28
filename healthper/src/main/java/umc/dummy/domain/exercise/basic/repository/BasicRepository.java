package umc.dummy.domain.exercise.basic.repository;

import umc.dummy.domain.exercise.basic.model.PostBasicExerciseReq;
import umc.dummy.domain.exercise.total.model.GetExerciseRes;

import java.util.List;

public interface BasicRepository {
    void add(PostBasicExerciseReq postBasicExerciseReq);
    List<GetExerciseRes> getBasicBySection(int section_id);
    void remove(Long exerciseId);
    void fix(Long exerciseId, PostBasicExerciseReq req);
    GetExerciseRes getExerciseInfo(Long exerciseId);
}
