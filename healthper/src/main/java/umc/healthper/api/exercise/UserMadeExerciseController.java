package umc.healthper.api.exercise;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import umc.healthper.domain.exercise.handmade.UserMadeService;
import umc.healthper.domain.exercise.handmade.model.PostMadeExerciseReq;
import umc.healthper.domain.exercise.total.GetExerciseRes;
import umc.healthper.domain.login.model.KakaoId;
import umc.healthper.global.argumentresolver.Login;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user-made")
public class UserMadeExerciseController {
    private final UserMadeService service;

    @PostMapping
    public String makeMyExercise(@Login KakaoId kakaoId, @RequestBody PostMadeExerciseReq req){
        service.makeMine(kakaoId.getId(), req);
        return "redirect:/exercise?sectionId="+req.getSection().getId();
    }
    
    @GetMapping("/{userMadeId}")
    public String fetchMyExercise(@PathVariable Long userMadeId, @RequestBody PostMadeExerciseReq req){
        service.fixMyExercise(userMadeId, req);
        return "redirect:/exercise?sectionId="+req.getSection().getId();
    }
    @DeleteMapping("/{userMadeId}")
    public String fetchMyExercise(@PathVariable Long userMadeId){
        int section = service.getMine(userMadeId).getSection().getId();
        service.delMyExercise(userMadeId);

        return "redirect:/exercise?sectionId="+section;
    }
}
