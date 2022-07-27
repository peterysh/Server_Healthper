package umc.healthper.domain.exercise.handmade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.healthper.domain.exercise.handmade.model.PostMadeExerciseReq;
import umc.healthper.domain.exercise.handmade.repository.UserMadeRepository;
import umc.healthper.domain.exercise.total.GetExerciseRes;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMadeService {
    private final UserMadeRepository repository;

    public GetExerciseRes getMine(Long user_made_id){
        return repository.getMyExerciseById(user_made_id);
    }
    public void makeMine(Long user_id, PostMadeExerciseReq req){
        Long idx = repository.makeExercise(user_id, req);
    }
    public List<GetExerciseRes> myList(Long user_id, int section_id){
        return repository.getMyListWithSectionId(user_id,section_id);
    }
    public void fixMyExercise(Long exercise_id, PostMadeExerciseReq req){
        repository.fixExerciseInfo(exercise_id, req.getName(), req.getSection().getId());
    }
    public void delMyExercise(Long exercise_id){
        repository.deleteMyExercise(exercise_id);
    }
}
