package umc.dummy.domain.exercise.total.userlikeRepository;

import org.springframework.stereotype.Repository;
import umc.dummy.domain.exercise.total.UserLike;
import umc.dummy.domain.exercise.total.model.GetUserLikeRes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

//@Repository
public class MemoryUserLikeRepository implements UserLikeRepository{

    private static Map<Long, UserLike> store = new ConcurrentHashMap<>();
    private static Long sequence = 0l;
    @Override
    public void doLike(Long exerciseId, Long userId, int sectionId) {
        ++sequence;
        Long priori = getLikeCount(userId, sectionId);
        store.put(sequence,new UserLike(sequence,exerciseId, userId, true, priori, sectionId));
    }

    @Override
    public void undoLike(Long userLikeId) {
        UserLike target = store.get(userLikeId);
        target.setLike(false);
    }
    private Long getLikeCount(Long userId, int sectionId){
        return store.values().stream().filter(e -> e.getLike() == true &&
                e.getUserId() == userId && e.getSection_id()== sectionId).count();
    }
    @Override
    public List<GetUserLikeRes> getLikeList(int sectionId, Long userId) {
        List<UserLike> likes = getUserLikes(sectionId, userId);
        List<GetUserLikeRes> res = new ArrayList<>();
        for (UserLike like : likes)
            res.add(new GetUserLikeRes(like.getUser_like_id(),like.getExerciseId(), like.getPriori(), like.getSection_id()));
        return res;
    }

    @Override
    public void changePriory(Long relationId, Long target) {

    }

    private List<UserLike> getUserLikes(int sectionId, Long userId) {
        List<UserLike> likes = store.values().stream()
                .filter(e -> e.getLike() == true && e.getSection_id() == sectionId && e.getUserId() == userId)
                .collect(Collectors.toCollection(ArrayList::new));

        Collections.sort(likes, (l1,l2)->{
            return l1.getPriori().compareTo(l2.getPriori());
        });
        return likes;
    }

    @Override
    public Long getOwner(Long userLikeId) {
        return store.get(userLikeId).getUserId();
    }

    //@Override
    public GetUserLikeRes getLikeInfo(Long userLikeId) {
        UserLike userLike = store.get(userLikeId);
        return new GetUserLikeRes(userLikeId, userLike.getExerciseId(), userLike.getPriori(), userLike.getSection_id());
    }
}
