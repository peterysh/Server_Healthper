package umc.healthper.domain.exercise.handmade.repository;

import org.springframework.stereotype.Repository;
import umc.healthper.Section;
import umc.healthper.domain.exercise.handmade.UserMade;
import umc.healthper.domain.exercise.handmade.model.PostMadeExerciseReq;
import umc.healthper.domain.exercise.total.GetExerciseRes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class MemoryUserMadeRepository implements UserMadeRepository{
    private static Map<Long, UserMade> store = new ConcurrentHashMap<>();
    private static Long sequence = 1000L;
    @Override
    public Long makeExercise(Long user_id, PostMadeExerciseReq exerciseReq) {
        sequence++;
        store.put(sequence, new UserMade(sequence, user_id,exerciseReq.getName(), exerciseReq.getSection().getId()));
        return sequence;
    }

    @Override
    public void fixExerciseInfo(Long user_made_id, String name, int sectionId) {
        UserMade userMade = store.get(user_made_id);
        userMade.setName(name);
        userMade.setSection_id(sectionId);
    }

    @Override
    public void deleteMyExercise(Long user_made_id) {
        store.remove(user_made_id);
    }

    @Override
    public List<GetExerciseRes> getMyListWithSectionId(Long user_id, int sectionId) {
        List<UserMade> myLoves= store.values().stream().filter(e -> e.getUser_id() == user_id && e.getSection_id() == sectionId)
                .collect(Collectors.toCollection(ArrayList::new));
        List<GetExerciseRes> res = new ArrayList<>();
        for (UserMade myLove : myLoves) {
            Long id = myLove.getUser_made_id();
            String name = myLove.getName();
            res.add(new GetExerciseRes(id,name, Section.getSectionById(sectionId)));
        }
        return res;
    }

    @Override
    public GetExerciseRes getMyExerciseById(Long user_made_id) {
        UserMade target = store.get(user_made_id);
        return new GetExerciseRes(user_made_id,target.getName(), Section.getSectionById(target.getSection_id()));
    }
}
