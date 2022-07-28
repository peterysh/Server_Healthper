package umc.dummy.domain.exercise.total;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.dummy.domain.exercise.basic.BasicExerciseService;
import umc.healthper.domain.exercise.UserMadeService;
import umc.dummy.domain.exercise.total.model.GetExerciseRes;
import umc.dummy.domain.exercise.total.model.GetUserLikeRes;
import umc.dummy.domain.exercise.total.model.PostChangePriroiReq;
import umc.dummy.domain.exercise.total.userlikeRepository.UserLikeRepository;
import umc.healthper.domain.exercise.model.GetMyExerciseRes;

import java.util.ArrayList;
import java.util.List;

//@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final UserMadeService userMadeService;
    private final BasicExerciseService basicService;
    private final UserLikeRepository repository;

    public List<GetMyExerciseRes> sectionList(Long userId, int sectionId){
        List<GetMyExerciseRes> myList = userMadeService.myList(userId, sectionId);
        List<GetExerciseRes> basicList = basicService.getExerciseBySectionId(sectionId);

        int len = myList.size() + basicList.size();

        List<GetExerciseRes> ret = new ArrayList<>();
        System.arraycopy(myList,0,ret,0,myList.size());
        System.arraycopy(basicList,0,ret,myList.size(),basicList.size());

        return null;
    }

    public List<GetUserLikeRes> favoriteList(Long userId, int sectionId){
        List<GetUserLikeRes> likeList = repository.getLikeList(sectionId, userId);
        return likeList;
    }

    public void doLike(Long userId, Long exerciseId){

    }
    public void unlike(Long userLikeId){
        int sectionId = -1;
        repository.undoLike(userLikeId);
    }

    public void changePriori(Long userLikeId, int sectionId, PostChangePriroiReq req){
        Long from = req.getFrom();
        Long to = req.getTo();
        if(from== to)return;
        if(from > to)
            ordering(userLikeId, sectionId, from, to);
        else
            reverseOrdering(userLikeId, sectionId, from, to);
    }

    public void ordering(Long userLikeId, int sectionId, Long to, Long from){
        Long owner = repository.getOwner(userLikeId);
        List<GetUserLikeRes> likeList = repository.getLikeList(sectionId, owner);
        for (GetUserLikeRes like : likeList) {
            if(like.getPriori() >= from && like.getPriori() < to)
                repository.changePriory(like.getExercise_id(), like.getPriori()+1);
        }
        repository.changePriory(userLikeId, to);
    }

    public void reverseOrdering(Long userLikeId, int sectionId, Long from , Long to){
        Long owner = repository.getOwner(userLikeId);
        List<GetUserLikeRes> likeList = repository.getLikeList(sectionId, owner);
        for (GetUserLikeRes like : likeList) {
            if(like.getPriori() > from && like.getPriori() <= to)
                repository.changePriory(like.getExercise_id(), like.getPriori()-1);
        }
        repository.changePriory(userLikeId, to);
    }
}
