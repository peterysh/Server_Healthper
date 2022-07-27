package umc.healthper.domain.exercise.basic.repository;

import umc.healthper.Section;
import umc.healthper.domain.exercise.basic.BasicExercise;
import umc.healthper.domain.exercise.basic.model.PostBasicExerciseReq;
import umc.healthper.domain.exercise.total.GetExerciseRes;

import java.util.List;

public interface BasicRepository {
    void add(PostBasicExerciseReq postBasicExerciseReq);
    List<GetExerciseRes> getBasicBySection(int section_id);
    void remove(Long exerciseId);
    void fix(Long exerciseId, PostBasicExerciseReq req);
}
