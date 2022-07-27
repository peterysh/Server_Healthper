package umc.healthper.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import umc.healthper.domain.login.model.KakaoId;
import umc.healthper.domain.routine.RoutineService;
import umc.healthper.domain.routine.model.GetRoutineRes;
import umc.healthper.domain.routine.model.PostRoutineReq;
import umc.healthper.domain.routine.model.PostRoutineRes;
import umc.healthper.global.argumentresolver.Login;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/routines")
@RequiredArgsConstructor
public class RoutineController {
    private final RoutineService routineService;

    @PostMapping
    @ResponseBody
    public PostRoutineRes makeRoutine(@Login Long kakaoId, @RequestBody PostRoutineReq req){
        PostRoutineRes res = routineService.pushRoutine(kakaoId, req);
        return res;
    }

    @GetMapping("/routine/{routineId}")
    @ResponseBody
    public GetRoutineRes getRoutineInfo(@PathVariable Long routineId){
        GetRoutineRes routineInfo = routineService.getRoutineInfo(routineId);
        return routineInfo;
    }

    @GetMapping
    @ResponseBody
    public List<GetRoutineRes> getRoutineList(@Login Long kakaoId){

        List<GetRoutineRes> routines = routineService.getRoutines(kakaoId);
        return routines;
    }
    
    @PatchMapping("/routine/{routineId}")
    public String fixRoutineInfo(@PathVariable Long routineId, @RequestBody PostRoutineReq req){
        routineService.fixRoutine(routineId, req);
        return "redirect:/routines/routine/"+routineId;
    }

    @PostMapping("routine/priori/{routineId}/{target}")
    public String fixRoutinesOrder(@PathVariable Long routineId, @PathVariable int target){
        routineService.reordering(routineId, target);
        Long userId = routineService.getOwner(routineId);
        return "redirect:/routines/"+userId;
    }

    @DeleteMapping("/{routineId}")
    public String deleteRoutine(@PathVariable Long routineId){
        routineService.deleteRoutine(routineId);
        Long userId = routineService.getOwner(routineId);
        return "redirect:/routines/"+userId;
    }
}
