package suman.AccountBook.domain.memo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import suman.AccountBook.domain.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "memo")
@Getter
public class Memo {

    @Id @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long memoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    @NotNull
    private Long price;

    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MemoType memoType;

    @NotNull
    private LocalDateTime date;

    @Column(name = "content")
    private String content;

    @Builder
    public Memo(User user,Long price,String name, MemoType memoType,LocalDateTime date,String content) {
        this.user = user;
        this.price = price;
        this.name = name;
        this.memoType = memoType;
        this.date = date;
        this.content = content;

    }

    public void UpdateMemo(Long price, String name, MemoType memoType, LocalDateTime date, String content){
        this.price = price;
        this.name = name;
        this.memoType = memoType;
        this.date = date;
        this.content = content;


    }

}
