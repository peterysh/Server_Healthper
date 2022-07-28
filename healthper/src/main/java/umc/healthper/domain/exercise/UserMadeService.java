package umc.healthper.domain.exercise;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.healthper.domain.exercise.model.GetMyExerciseRes;
import umc.healthper.domain.exercise.model.PostMadeExerciseReq;
import umc.healthper.domain.exercise.repository.UserMadeRepository;
import umc.dummy.domain.exercise.total.model.GetExerciseRes;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMadeService {
    private final UserMadeRepository repository;

    public GetMyExerciseRes getMine(Long user_made_id){
        return repository.getMyExerciseById(user_made_id);
    }
    public Long makeMine(Long user_id, PostMadeExerciseReq req){
        Long exerciseId = repository.makeExercise(user_id, req);
        return exerciseId;
    }
    public List<GetMyExerciseRes> myList(Long user_id, int section_id){
        return repository.getMyListWithSectionId(user_id,section_id);
    }
    public void fixMyExercise(Long exercise_id, PostMadeExerciseReq req){
        repository.fixExerciseInfo(exercise_id, req.getName(), req.getSection().getId());
    }
    public void delMyExercise(Long exercise_id){
        repository.deleteMyExercise(exercise_id);
    }
}
