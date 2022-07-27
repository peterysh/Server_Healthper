package umc.healthper.domain.exercise.basic;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umc.healthper.Section;
import umc.healthper.domain.exercise.basic.model.PostBasicExerciseReq;
import umc.healthper.domain.exercise.basic.repository.BasicRepository;
import umc.healthper.documents.DocumentList;
import umc.healthper.domain.exercise.total.GetExerciseRes;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
@Slf4j
@RequiredArgsConstructor
public class BasicExerciseService {
    private final BasicRepository basicRepository;

    @PostConstruct
    public void makeBasicList() throws FileNotFoundException {
        List<PostBasicExerciseReq> basicExerciseReqs = readFile();
        for (PostBasicExerciseReq basicList : basicExerciseReqs) {
            basicRepository.add(basicList);
        }
    }

    public List<PostBasicExerciseReq> readFile() throws FileNotFoundException {
        File doc = new File(DocumentList.basic_list);
        Scanner obj = new Scanner(doc);

        List<PostBasicExerciseReq> basicList = new ArrayList<>();
        while (obj.hasNextLine()) {
            String str = obj.nextLine();
            if(str == "-")continue;
            String[] array = str.split(":");

            if(array.length == 2) {
                Section s = Section.getSectionById(Integer.parseInt(array[1]));
                basicList.add(new PostBasicExerciseReq(array[0],s));
            }
        }
        return basicList;
    }

    public List<GetExerciseRes> getExerciseBySectionId(int sectionId){
        return basicRepository.getBasicBySection(sectionId);
    }


    public void fixExercise(Long exerciseId, PostBasicExerciseReq req){
        basicRepository.fix(exerciseId, req);
    }

    public void deleteExercise(Long exerciseId){
        basicRepository.remove(exerciseId);
    }
}
