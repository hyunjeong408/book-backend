package com.example.bookback.service;

import com.example.bookback.dto.SentencePostRequestDto;
import com.example.bookback.dto.SentenceResponseDto;
import com.example.bookback.entity.*;
import com.example.bookback.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class SentenceService {
    private final MemberRepository memberRepository;
    private final HashtagRepository tagRepository;
    private final SentenceRepository sentenceRepository;
    private final LikeSentenceRepository likeSentenceRepository;
    private final UserLikesRepository userLikesRepository;

    public SentenceResponseDto post(SentencePostRequestDto requestDto, Integer user_sn, Integer tag_id){
        Member writer = memberRepository.findByUserSn(user_sn).orElseThrow(()->new UsernameNotFoundException("이용자를 DB에서 찾을 수 없습니다"));
        Hashtag tag = tagRepository.findByTagId(tag_id).orElseThrow();
        Sentence sentence = Sentence.builder()
                .content(requestDto.getContent())
                .writer(writer)
                .title(requestDto.getBookTitle())
                .b_writer(requestDto.getBookWriter())
                .tag(tag)
                .updateDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        return new SentenceResponseDto(sentenceRepository.save(sentence));
    }

    public Boolean chkSentenceLiked(Integer id_sentence, Integer user_sn){
        if (likeSentenceRepository.findBySentence_IdAndUser_UserSn(id_sentence, user_sn) == null){
            return false;
        } else{
            return true;
        }
    }

    @Transactional
    public Boolean updateLikes(Integer id_sentence, Integer user_sn, Integer update_n){
        Sentence sentence = sentenceRepository.findById(id_sentence).orElseThrow();
        Member user = memberRepository.findByUserSn(user_sn).orElseThrow(()->new UsernameNotFoundException("이용자를 DB에서 찾을 수 없습니다"));
        if (likeSentenceRepository.findBySentence_IdAndUser_UserSn(id_sentence, user_sn) == null) { //추천하지 않은 경우
            LikeSentence likeSentence = LikeSentence.builder()
                    .sentence(sentence)
                    .user(user)
                    .build();
            likeSentenceRepository.save(likeSentence);
            sentence.updateLikeNum(update_n);
            Optional<UserLikes> userLikes = userLikesRepository.findByUser_UserSn(user_sn);
            if(userLikes.isEmpty()){
                UserLikes newUserLikes = UserLikes.builder().user(user).build();
                newUserLikes.updateLikes(sentence.getHashtag().getTagId());
                userLikesRepository.save(newUserLikes);
            }
            else{
                if(update_n>0){
                    userLikes.get().updateLikes(sentence.getHashtag().getTagId());
                }else{
                    userLikes.get().deleteLikes(sentence.getHashtag().getTagId());
                }
            }
            return true;
        }
        else {
            if(update_n<0){
                LikeSentence like = likeSentenceRepository.findBySentence_IdAndUser_UserSn(id_sentence, user_sn);
                sentence.updateLikeNum(update_n);
                likeSentenceRepository.delete(like);
                Optional<UserLikes> userLikes = userLikesRepository.findByUser_UserSn(user_sn);
                userLikes.get().deleteLikes(sentence.getHashtag().getTagId());
            }
            return false;
        }
    }
    public List<Sentence> getAllSentences(){
        return sentenceRepository.findAllByOrderByUpdateDateDesc();
    }

    public List<Sentence> getTop10Sentences() { return sentenceRepository.findTop10ByOrderByUpdateDateDesc(); }
    public List<Sentence> getSentencesToRecommend(Integer user_sn){
        //현재 유저 좋아요 기록 가져와 저장해두기
        Optional<UserLikes> userLikes = userLikesRepository.findByUser_UserSn(user_sn);
        if(userLikes.isEmpty()){
            //최근 글 추천
            return sentenceRepository.findTop10ByOrderByUpdateDateDesc();
        }
        if(userLikes.get().getUserLikesCnt()<5){//좋아요 기록이 5회 미만: 최근 글 추천
            return sentenceRepository.findTop10ByOrderByUpdateDateDesc();
        }

        UserLikes userLikesNotEmpty = userLikes.get();
        List<UserLikes> allUserLikes = userLikesRepository.findAll();

        double maxCos = -1;
        Integer maxOtherSn = user_sn;
        int[] myLikesArr = userLikesNotEmpty.getUserLikesArray();

        //모든 likes 배열 loop 돌면서
        for(UserLikes ul: allUserLikes){
            if (ul.getUserSn() == user_sn){ continue; }
            int[] othersLikesArr = ul.getUserLikesArray();
            double a = 0; double b = 0;
            double aSameB = 0;
            for(int i = 0; i<9;i++){
                a += Math.pow(myLikesArr[i], 2);
                b += Math.pow(othersLikesArr[i], 2);
                if(myLikesArr[i]!=0 && othersLikesArr[i]!=0){
                    aSameB += myLikesArr[i]*othersLikesArr[i];
                }
            }

            double cos = aSameB/(Math.sqrt(a)*Math.sqrt(b));
            System.out.println("["+user_sn+":"+ul.getUserSn()+"]"+cos);
            if (maxCos<cos){
                maxCos = cos;
                maxOtherSn = ul.getUserSn();
            }
        }

        ArrayList<Sentence> tmpRecList = new ArrayList<>();
        //상대가 좋아요 누른 게시물 리스트 받아오기
        List<LikeSentence> othersLikeSentences = likeSentenceRepository.findAllByUser_UserSn(maxOtherSn);

        for(LikeSentence ls: othersLikeSentences){
            if(likeSentenceRepository.findBySentence_IdAndUser_UserSn(ls.getSentence().getId(), user_sn)==null){
                tmpRecList.add(ls.getSentence());
            }
        }
        int recListSize = tmpRecList.size();
        ArrayList<Sentence> newRecList = new ArrayList<>();

        int[] randNum = new int[4];

        if(recListSize>=4){
            Random r = new Random();
            for(int k=0;k<4;k++){
                randNum[k] = r.nextInt(recListSize);
                for(int p=0;p<k;p++){
                    if(randNum[k]==randNum[p]){ k--; }
                }
            }
            for(Integer randIdx:randNum){
                newRecList.add(tmpRecList.get(randIdx));
            }
            Integer cnt = 0;
            for(Sentence newS: sentenceRepository.findTop10ByOrderByUpdateDateDesc()){
                if(cnt>=4){
                    break;
                }
                Boolean chk = true;
                for(Sentence nowList: newRecList){
                    if(nowList.getId()==newS.getId()){
                        chk = false;
                        break;
                    }
                }
                if(chk) {
                    newRecList.add(newS);
                    cnt+=1;
                }
            }
        }
        else{
            for(Sentence tmpS: tmpRecList){
                newRecList.add(tmpS);
            }
            Integer cnt = 0;
            for(Sentence newS: sentenceRepository.findTop10ByOrderByUpdateDateDesc()){
                if(cnt>=(4-recListSize)+4){
                    break;
                }
                Boolean chk = true;
                for(Sentence nowList: newRecList){
                    if(nowList.getId()==newS.getId()){
                        chk = false;
                        break;
                    }
                }
                if(chk) {
                    newRecList.add(newS);
                    cnt+=1;
                }
            }


        }

        return newRecList;

    }
}
