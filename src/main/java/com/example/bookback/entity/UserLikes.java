package com.example.bookback.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@DynamicInsert
@Table(name="userlikes")
public class UserLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Integer id;

    @ManyToOne // Many = Board, User = One 한명의 유저는 여러개의 게시글을 쓸 수 있다.
    @JoinColumn(name="user", nullable = false)
    private Member user;

    @Column(name="etc")
    private Integer etc = 0;

    @Column(name="love")
    private Integer love = 0;

    @Column(name="growth")
    private Integer growth = 0;

    @Column(name="horror")
    private Integer horror = 0;

    @Column(name="art")
    private Integer art = 0;

    @Column(name="life")
    private Integer life = 0;

    @Column(name="comedy")
    private Integer comedy = 0;

    @Column(name="reality")
    private Integer reality = 0;

    @Column(name="fantasy")
    private Integer fantasy = 0;

    @Builder
    public UserLikes(Member user){
        this.user = user;
        this.etc = 0;
        this.love = 0;
        this.growth = 0;
        this.horror = 0;
        this.art = 0;
        this.life = 0;
        this.comedy = 0;
        this.reality = 0;
        this.fantasy = 0;
    }

    public int getUserSn(){
        return this.user.getUserSn();
    }

    public int[] getUserLikesArray(){
        int[] arr = new int[9];
        arr[0] = this.etc;
        arr[1] = this.love;
        arr[2] = this.growth;
        arr[3] = this.horror;
        arr[4] = this.art;
        arr[5] = this.life;
        arr[6] = this.comedy;
        arr[7] = this.reality;
        arr[8] = this.fantasy;
        return arr;
    }

    public int getUserLikesCnt(){
        return this.etc+this.love+this.growth+this.horror+this.art+this.life+this.comedy+this.reality+this.fantasy;
    }

    public void updateLikes(Integer tag_id){
        switch (tag_id){
            case 1:
                this.love += 1; break;
            case 2:
                this.growth += 1; break;
            case 3:
                this.horror += 1; break;
            case 4:
                this.art += 1; break;
            case 5:
                this.life += 1; break;
            case 6:
                this.comedy += 1; break;
            case 7:
                this.reality += 1; break;
            case 8:
                this.fantasy += 1; break;
            default:
                this.etc += 1; break;
        }

    }

    public void deleteLikes(Integer tag_id){
        switch (tag_id){
            case 1:
                this.love -= 1; break;
            case 2:
                this.growth -= 1; break;
            case 3:
                this.horror -= 1; break;
            case 4:
                this.art -= 1; break;
            case 5:
                this.life -= 1; break;
            case 6:
                this.comedy -= 1; break;
            case 7:
                this.reality -= 1; break;
            case 8:
                this.fantasy -= 1; break;
            default:
                this.etc -= 1; break;
        }

    }


}
