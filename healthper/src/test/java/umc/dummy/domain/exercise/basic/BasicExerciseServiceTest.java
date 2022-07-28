package umc.dummy.domain.exercise.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import umc.healthper.Section;
import umc.dummy.domain.exercise.basic.model.PostBasicExerciseReq;
import umc.dummy.domain.exercise.basic.repository.BasicRepository;
import umc.dummy.domain.exercise.basic.repository.MemoryBasicRepository;
import umc.dummy.domain.exercise.total.model.GetExerciseRes;

import java.io.FileNotFoundException;
import java.util.List;


@Slf4j

class BasicExerciseServiceTest {
    BasicExerciseService basicExerciseService;
    BasicRepository basicRepository;
    @BeforeEach
    void before() throws FileNotFoundException {
        basicRepository = new MemoryBasicRepository();
        basicExerciseService = new BasicExerciseService(basicRepository);
        basicExerciseService.makeBasicList();
    }

    @Test
    void fueling() throws FileNotFoundException {
        basicExerciseService.readFile();
    }
    @Test
    void getList(int x){
        List<GetExerciseRes> exerciseBySectionId = basicExerciseService.getExerciseBySectionId(x);
        for (GetExerciseRes basicExercise : exerciseBySectionId) {
            log.info("{},{},{}", basicExercise.getExercise_id(), basicExercise.getName(), basicExercise.getSection());
        }
    }
    @Test
    void fixElements(){
        getList(4);
        basicRepository.fix(11l,new PostBasicExerciseReq("밆프", Section.ABS));
        getList(5);
    }
    @Test
    void remove(){
        basicRepository.remove(11l);
        getList(4);
    }
}