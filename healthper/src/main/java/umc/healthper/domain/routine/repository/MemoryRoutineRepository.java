package umc.healthper.domain.routine.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import umc.healthper.Section;
import umc.healthper.domain.routine.Routine;
import umc.healthper.domain.routine.model.GetRoutineRes;
import umc.healthper.domain.routine.model.PostRoutineReq;
import umc.healthper.domain.routine.model.PostRoutineRes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class MemoryRoutineRepository implements RoutineRepository {
    private static Map<Long, Routine> store = new ConcurrentHashMap<>();
    private static Long sequence = 0l;

    @Override
    public PostRoutineRes save(Long userId, PostRoutineReq postRoutineReq) {
        String name = postRoutineReq.getName();
        String sections = sectionListToStr(postRoutineReq.getSections());
        int count = countRoutine(userId).size();

        Routine routine = new Routine(++sequence, userId, name, sections, count);
        store.put(sequence, routine);

        log.info("{sections_str}, {sections}",sections, postRoutineReq.getSections());
        return new PostRoutineRes(routine.getName(), postRoutineReq.getSections(), routine.getPriori());
    }

    private List<Routine> countRoutine(Long userId) {
        ArrayList<Routine> routines = store.values().stream()
                .filter(r -> r.getUserId() == userId)
                .collect(Collectors.toCollection(ArrayList::new));
        return routines;
    }

    @Override
    public GetRoutineRes getRoutine(Long routineId) {
        Routine routine = store.get(routineId);
        GetRoutineRes response = new GetRoutineRes(routine.getId(), routine.getName(),
                strToSectionList(routine.getSections()), routine.getPriori());
        return response;
    }

    @Override
    public List<GetRoutineRes> getAll(Long userId) {
        List<Routine> routines = countRoutine(userId);
        List<GetRoutineRes> response = RoutineToRes(routines);
        return response;
    }

    private List<GetRoutineRes> RoutineToRes(List<Routine> routines) {
        List<GetRoutineRes> response = new ArrayList<>();
        for (Routine routine : routines) {
            Long id = routine.getId();
            String name = routine.getName();
            List<Section> sections = strToSectionList(routine.getSections());
            int priori = routine.getPriori();
            response.add(new GetRoutineRes(id,name,sections,priori));
        }
        return response;
    }

    @Override
    public void changeInfo(Long routineId, PostRoutineReq postRoutineReq) {
        Routine routine = store.get(routineId);
        routine.setName(postRoutineReq.getName());
        String sections = sectionListToStr(postRoutineReq.getSections());
        routine.setSections(sections);
    }

    @Override
    public void changePriory(Long routineId, int target) {
        Routine routine = store.get(routineId);
        routine.setPriori(target);
    }

    @Override
    public void deleteRoutine(Long routineId) {
        store.remove(routineId);
    }

    @Override
    public Long getOwner(Long routineId) {
        return store.get(routineId).getUserId();
    }

    private List<Section> strToSectionList(String sections){
        List<Section> res = new ArrayList<>();
        for(int i=0;i<sections.length();i++){
            if(sections.charAt(i) == '1')
                res.add(Section.getSectionById(i));
        }
        return res;
    }
    private String sectionListToStr(List<Section> sections){
        String res = "";
        int[] arr = new int[10];
        for (Section section : sections) {
            int idx = section.getId();
            arr[idx] = 1;
        }
        return arr.toString();
    }
}
