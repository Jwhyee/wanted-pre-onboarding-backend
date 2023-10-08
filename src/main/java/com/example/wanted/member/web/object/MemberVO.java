package com.example.wanted.member.web.object;

import com.example.wanted.member.domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberVO {

    private Long id;
    private String username;

    public static MemberVO fromEntity(Member member) {
        return builder()
                .id(member.getId())
                .username(member.getUsername())
                .build();
    }
}
