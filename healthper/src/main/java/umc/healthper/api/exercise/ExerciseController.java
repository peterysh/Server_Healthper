package umc.healthper.api.exercise;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import umc.healthper.domain.exercise.basic.BasicExerciseService;
import umc.healthper.domain.exercise.handmade.UserMadeService;
import umc.healthper.domain.exercise.total.GetExerciseRes;
import umc.healthper.domain.exercise.total.model.GetUserLikeRes;
import umc.healthper.domain.login.model.KakaoId;
import umc.healthper.global.argumentresolver.Login;

import java.util.List;

@Controller
@RequestMapping("/exercise")
@RequiredArgsConstructor
public class ExerciseController { //qweqwe

    @GetMapping
    public List<GetExerciseRes> exerciseBySection(@Login KakaoId kakaoId, @RequestParam("section") Long sectionId){
        return null;
    }
    @GetMapping("/love")
    public List<GetUserLikeRes> likeBySection(@Login KakaoId kakaoId, @RequestParam("section") Long sectionId){
        return null;
    }
}
