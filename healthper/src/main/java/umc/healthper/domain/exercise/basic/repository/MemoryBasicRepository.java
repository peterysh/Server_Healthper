package umc.healthper.domain.exercise.basic.repository;

import org.springframework.stereotype.Repository;
import umc.healthper.Section;
import umc.healthper.domain.exercise.basic.BasicExercise;
import umc.healthper.domain.exercise.basic.model.PostBasicExerciseReq;
import umc.healthper.domain.exercise.total.GetExerciseRes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class MemoryBasicRepository implements BasicRepository {
    private static Map<Long, BasicExercise> store = new ConcurrentHashMap<>();
    private static Long sequence = 0l;
    @Override
    public void add(PostBasicExerciseReq postBasicExerciseReq) {
        ++sequence;
        String name = postBasicExerciseReq.getName();
        int section = postBasicExerciseReq.getSection().getId();
        store.put(sequence,new BasicExercise(sequence, name, section));
    }

    @Override
    public List<GetExerciseRes> getBasicBySection(int sectionId) {
        List<BasicExercise> exercises = store.values().stream().filter(e -> e.getSection_id() == sectionId)
                .collect(Collectors.toCollection(ArrayList::new));
        List<GetExerciseRes> res = new ArrayList<>();
        for (BasicExercise exercise : exercises) {
            Long exercise_id = exercise.getId();
            String name = exercise.getName();
            Section section = Section.getSectionById(exercise.getSection_id());
            res.add(new GetExerciseRes(exercise_id, name, section));
        }
        return res;
    }

    @Override
    public void remove(Long exerciseId) {
        store.remove(exerciseId);
    }

    @Override
    public void fix(Long exerciseId, PostBasicExerciseReq req) {
        BasicExercise exercise = store.get(exerciseId);
        exercise.setName(req.getName());
        exercise.setSection_id(req.getSection().getId());
    }
}
