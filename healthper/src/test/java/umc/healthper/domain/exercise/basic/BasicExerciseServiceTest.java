package umc.healthper.domain.exercise.basic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import umc.healthper.Section;
import umc.healthper.domain.exercise.basic.model.PostBasicExerciseReq;
import umc.healthper.domain.exercise.basic.repository.BasicRepository;
import umc.healthper.domain.exercise.basic.repository.MemoryBasicRepository;
import umc.healthper.domain.exercise.total.GetExerciseRes;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


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