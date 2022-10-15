package hello.springmvc.basic;

import lombok.Data;

@Data
// @Getter @Setter @ToString @EqualAndHashCode @RequiredArgsConstructor 를 자동 적
public class HelloData {
    private String username;
    private int age;
}
