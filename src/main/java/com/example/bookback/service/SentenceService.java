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
import java.util.List;
import java.util.Optional;

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
}
