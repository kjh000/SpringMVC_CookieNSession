package hello.login.web.session;

import hello.login.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

//@RequiredArgsConstructor 
class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();
//    private final SessionManager sessionManager; // 에러남
    
    @Test
    void sessionTest(){


        //세션 생성
        MockHttpServletResponse response = new MockHttpServletResponse(); // 테스트용 가짜 서블릿
        Member member = new Member();
        sessionManager.createSession(member,response);
        
        //요청에 응답 쿠키 저장
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());
        
        //세션 조회
        Object result = sessionManager.getSession(request);
        assertThat(result).isEqualTo(member);

        //세션 만료
        sessionManager.expire(request);
        Object expired = sessionManager.getSession(request);
        assertThat(expired).isNull();
    }
    
    @Test
    void createSession() {
    }

    @Test
    void getSession() {
    }

    @Test
    void expire() {
    }

    @Test
    void findCookie() {
    }
}