package umc.healthper.domain.exercise.repository;

import umc.healthper.domain.exercise.model.GetMyExerciseRes;
import umc.healthper.domain.exercise.model.PostMadeExerciseReq;
import umc.dummy.domain.exercise.total.model.GetExerciseRes;

import java.util.List;

public interface UserMadeRepository {
    Long makeExercise(Long user_id, PostMadeExerciseReq exerciseReq);
    void fixExerciseInfo(Long user_made_id, String name, int sectionId);
    void deleteMyExercise(Long user_made_id);
    List<GetMyExerciseRes> getMyListWithSectionId(Long user_id, int sectionId);
    GetMyExerciseRes getMyExerciseById(Long user_made_id);
}
