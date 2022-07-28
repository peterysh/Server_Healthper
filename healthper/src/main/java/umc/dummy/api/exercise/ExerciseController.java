package umc.dummy.api.exercise;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import umc.dummy.domain.exercise.total.model.GetExerciseRes;
import umc.dummy.domain.exercise.total.model.GetUserLikeRes;
import umc.healthper.global.argumentresolver.Login;

import java.util.List;

@Controller
@RequestMapping("/exercise")
@RequiredArgsConstructor
public class ExerciseController { //qweqwe

    @GetMapping
    public List<GetExerciseRes> exerciseBySection(@Login Long kakaoId, @RequestParam("section") Long sectionId){
        return null;
    }
    @GetMapping("/love")
    public List<GetUserLikeRes> likeBySection(@Login Long kakaoId, @RequestParam("section") Long sectionId){
        return null;
    }

}
