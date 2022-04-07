package suman.store.domain.memo;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemoDto {

    public Long userId;
    public Long price;
    public String name;
    public MemoType memoType;
    public LocalDateTime date;
    public String content;
}
