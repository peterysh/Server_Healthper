package umc.healthper.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import umc.dummy.domain.exercise.total.model.GetExerciseRes;
import umc.healthper.domain.exercise.UserMadeService;
import umc.healthper.domain.exercise.model.GetMyExerciseRes;
import umc.healthper.domain.exercise.model.PostMadeExerciseReq;
import umc.healthper.domain.login.model.KakaoId;
import umc.healthper.global.argumentresolver.Login;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user-made")
@Slf4j
public class UserMadeExerciseController {
    private final UserMadeService service;

    @PostMapping
    public String makeMyExercise(@Login Long kakaoId, @RequestBody PostMadeExerciseReq req){
        //log.info("{}",req.toString());
        Long userMadeId = service.makeMine(kakaoId, req);

        return "redirect:/user-made/info/"+userMadeId;
    }

    @GetMapping("/info/{userMadeId}")
    @ResponseBody
    public GetMyExerciseRes getMyExerciseRes(@PathVariable Long userMadeId){
        return service.getMine(userMadeId);
    }

    @PatchMapping("/{userMadeId}")
    public String fetchMyExercise(@PathVariable Long userMadeId, @RequestBody PostMadeExerciseReq req){
        service.fixMyExercise(userMadeId, req);

        return "redirect:/user-made/info/"+userMadeId;
    }
    @DeleteMapping("/{userMadeId}")
    public String deleteMyExercise(@PathVariable Long userMadeId){
        int section = service.getMine(userMadeId).getSection().getId();
        service.delMyExercise(userMadeId);

        return "redirect:/user-made/"+section;
    }
    @GetMapping("/{sectionId}")
    @ResponseBody
    public List<GetMyExerciseRes> myListBySectionId(@Login Long kakaoKey, @PathVariable int sectionId){
        return service.myList(kakaoKey, sectionId);
    }

}
