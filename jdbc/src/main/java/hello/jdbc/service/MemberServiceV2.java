package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 트랜젝션 - 파라미터 연동, 풀을 고려 종료
 */
@RequiredArgsConstructor
@Slf4j
public class MemberServiceV2 {

    private final DataSource dataSource;
    private final MemberRepositoryV2 memberRepository;

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        // connection 얻기
        Connection connection = dataSource.getConnection();
        try {
            connection.setAutoCommit(false); // 트랜젝션 시작 = 자동커밋 모드 끄기
            // 비지니스 로직 수행
            bizLogic(connection, fromId, toId, money);
            connection.commit(); // 성공시 커밋
        } catch (Exception e){
            connection.rollback();
            throw  new IllegalStateException(e);
        }finally {
            release(connection);

        }
    }

    private void bizLogic(Connection connection, String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepository.findById(connection, fromId);
        Member toMember = memberRepository.findById(connection, toId);

        memberRepository.update(connection, fromId,fromMember.getMoney()- money);
        validation(toMember);
        memberRepository.update(connection, toId,toMember.getMoney()+ money);
    }

    private void release(Connection connection) {
        if(connection != null){
            try {
                connection.setAutoCommit(true); // connection 의 자동커밋을 트루로 바꾸고 종료해줘야 풀로 돌아간 커넥션이 기본값(autocommit = false)과 일치하게 됨
                connection.close();
            } catch (Exception e){
                log.info("error",e);
            }
        }
    }

    private void validation(Member toMember) {
        if (toMember.getMemberId().equals("ex")){
            throw new IllegalStateException("이체 중 예외 발생");
        }
    }
}
