package umc.healthper.domain.exercise.handmade.repository;

import umc.healthper.domain.exercise.handmade.model.PostMadeExerciseReq;
import umc.healthper.domain.exercise.total.GetExerciseRes;

import java.util.List;

public interface UserMadeRepository {
    Long makeExercise(Long user_id, PostMadeExerciseReq exerciseReq);
    void fixExerciseInfo(Long user_made_id, String name, int sectionId);
    void deleteMyExercise(Long user_made_id);
    List<GetExerciseRes> getMyListWithSectionId(Long user_id, int sectionId);
    GetExerciseRes getMyExerciseById(Long user_made_id);
}
