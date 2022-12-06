package shop.ggamf.ggamf.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.ggamf.ggamf.config.auth.LoginUser;
import shop.ggamf.ggamf.domain.user.User;
import shop.ggamf.ggamf.domain.user.UserRepository;
import shop.ggamf.ggamf.dto.UserReqDto.JoinReqDto;
import shop.ggamf.ggamf.dto.UserReqDto.UpdateReqDto;
import shop.ggamf.ggamf.dto.UserRespDto.JoinRespDto;
import shop.ggamf.ggamf.dto.UserRespDto.UpdateRespDto;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public JoinRespDto 회원가입(JoinReqDto joinReqDto) {

        // 1. 비밀번호 암호화
        String rawPassword = joinReqDto.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword);
        joinReqDto.setPassword(encPassword);
        // 2. 회원가입
        User userPS = userRepository.save(joinReqDto.toEntity());
        // 3. DTO 응답
        return new JoinRespDto(userPS);
    }

    @Transactional
    public UpdateRespDto 회원정보수정(UpdateReqDto updateReqDto, Long id) {
        //1. update
        User userPS = userRepository.updateById(id);
        //2. DTO 응답

        return new UpdateRespDto(userPS);
    }

    @Transactional
    public void 사진수정() {
        //1. user가 본인인지 체크
        //2. 사진 수정
        //3. Dto리턴
    }

    @Transactional
    public void 자기소개수정() {
        //1. user가 본인인지 체크
        //2. 자기소개 수정
        //3. Dto리턴
    }

    @Transactional
    public void 닉네임수정() {
        //1. user가 본인인지 체크
        //2. 닉네임 수정
        //3. Dto리턴
    }
    
    @Transactional
    public void 전화번호수정() {
        //1. user가 본인인지 체크
        //2. 전화번호 수정
        //3. Dto리턴
    }
    
    @Transactional
    public void 이메일수정() {
        //1. user가 본인인지 체크
        //2. 이메일 수정
        //3. Dto리턴
    }

    @Transactional
    public void 회원탈퇴() {
        //1. user가 본인인지 체크
        //2. user의 state를 '탈퇴' 로 바꾸기
    }


    @Transactional
    public void 회원영구삭제(Long id) {
        //1. ROLE이 admin이고
        //2. user의 state가 '탈퇴' 상태이면
        //3. 회원 영구삭제
        userRepository.deleteById(id);
    }
}
