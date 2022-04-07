package suman.store.domain.memo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import suman.store.domain.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "memo")
@Getter @Setter
public class Memo {

    @Id @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long memoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "userId")
    private User user;

    @NotNull
    private Long price;

    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MemoType memoType;

    @NotNull
    private LocalDateTime Date;

    public static Memo createMemo(User user,Memo memoParam){
        Memo memo = new Memo();
        memo.setUser(user);
        memo.setName(memoParam.getName());
        memo.setMemoType(memoParam.getMemoType());
        memo.setPrice(memoParam.getPrice());
        memo.setDate(memoParam.getDate());
        return memo;
    }



}
