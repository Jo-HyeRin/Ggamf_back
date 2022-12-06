package shop.ggamf.ggamf.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.ggamf.ggamf.config.auth.LoginUser;
import shop.ggamf.ggamf.config.exception.CustomApiException;
import shop.ggamf.ggamf.domain.user.User;
import shop.ggamf.ggamf.domain.user.UserRepository;
import shop.ggamf.ggamf.dto.UserReqDto.JoinReqDto;
import shop.ggamf.ggamf.dto.UserReqDto.UpdateEmailReqDto;
import shop.ggamf.ggamf.dto.UserReqDto.UpdateIntroReqDto;
import shop.ggamf.ggamf.dto.UserReqDto.UpdateNicknameReqDto;
import shop.ggamf.ggamf.dto.UserReqDto.UpdatePasswordReqDto;
import shop.ggamf.ggamf.dto.UserReqDto.UpdatePhoneReqDto;
import shop.ggamf.ggamf.dto.UserReqDto.UpdatePhotoReqDto;
import shop.ggamf.ggamf.dto.UserReqDto.UpdateStateReqDto;
import shop.ggamf.ggamf.dto.UserRespDto.JoinRespDto;
import shop.ggamf.ggamf.dto.UserRespDto.UpdateEmailRespDto;
import shop.ggamf.ggamf.dto.UserRespDto.UpdateIntroRespDto;
import shop.ggamf.ggamf.dto.UserRespDto.UpdateNicknameRespDto;
import shop.ggamf.ggamf.dto.UserRespDto.UpdatePasswordRespDto;
import shop.ggamf.ggamf.dto.UserRespDto.UpdatePhoneRespDto;
import shop.ggamf.ggamf.dto.UserRespDto.UpdateStateRespDto;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
    
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private static LoginUser loginuser;

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

    // @Transactional
    // public UpdateRespDto 회원정보수정(UpdateReqDto updateReqDto, Long id) {
    //     //1. update
    //     User userPS = userRepository.updateById(id);
    //     //2. DTO 응답

    //     return new UpdateRespDto(userPS);
    // }

    @Transactional
    public void 사진수정(UpdatePhotoReqDto updatePhotoReqDto, Long id) {
        //1. user가 본인인지 체크
        Optional<User> userOP = userRepository.findById(updatePhotoReqDto.getId());
        if (!userOP.isPresent()) {
            userRepository.findById(updatePhotoReqDto.getId())
                    .orElseThrow(() -> (new CustomApiException("해당유저가 존재하지 않습니다.", HttpStatus.BAD_REQUEST)));
        }
        //2. 사진 수정
        //3. Dto리턴
    }

    @Transactional
    public UpdateIntroRespDto 자기소개수정(UpdateIntroReqDto updateIntroReqDto) {
        //1. user가 본인인지 체크
        Optional<User> userOP = userRepository.findById(updateIntroReqDto.getId());
        if (!userOP.isPresent()) {
            userRepository.findById(updateIntroReqDto.getId())
                    .orElseThrow(() -> (new CustomApiException("해당유저가 존재하지 않습니다.", HttpStatus.BAD_REQUEST)));
        }
        //2. 수정
        User userPS = userOP.get();
        userPS.자기소개수정(updateIntroReqDto.getIntro());
        //3. Dto리턴
        return new UpdateIntroRespDto(userPS);
    }

    @Transactional
    public UpdateNicknameRespDto 닉네임수정(UpdateNicknameReqDto updateNicknameReqDto, Long id) {
        //1. user가 본인인지 체크
        Optional<User> userOP = userRepository.findById(updateNicknameReqDto.getId());
        if (!userOP.isPresent()) {
            userRepository.findById(updateNicknameReqDto.getId())
                    .orElseThrow(() -> (new CustomApiException("해당유저가 존재하지 않습니다.", HttpStatus.BAD_REQUEST)));
        }
        //2. 닉네임 수정
        User userPS = userOP.get();
        userPS.닉네임수정(updateNicknameReqDto.getNickname());
        //3. Dto리턴
        return new UpdateNicknameRespDto(userPS);
    }
    
    @Transactional
    public UpdatePasswordRespDto 비밀번호수정(UpdatePasswordReqDto updatePasswordReqDto, Long id) {
        //1. user가 본인인지 체크
        Optional<User> userOP = userRepository.findById(updatePasswordReqDto.getId());
        if (!userOP.isPresent()) {
            userRepository.findById(updatePasswordReqDto.getId())
                    .orElseThrow(() -> (new CustomApiException("해당유저가 존재하지 않습니다.", HttpStatus.BAD_REQUEST)));
        }
        //2. 비밀번호 수정
        User userPS = userOP.get();
        userPS.비밀번호수정(updatePasswordReqDto.getPassword());
        //3. Dto리턴
        return new UpdatePasswordRespDto(userPS);
    }

    @Transactional
    public UpdatePhoneRespDto 전화번호수정(UpdatePhoneReqDto updatePhoneReqDto, Long id) {
        //1. user가 본인인지 체크
        Optional<User> userOP = userRepository.findById(updatePhoneReqDto.getId());
        if (!userOP.isPresent()) {
            userRepository.findById(updatePhoneReqDto.getId())
                    .orElseThrow(() -> (new CustomApiException("해당유저가 존재하지 않습니다.", HttpStatus.BAD_REQUEST)));
        }
        //2. 전화번호 수정
        User userPS = userOP.get();
        userPS.전화번호수정(updatePhoneReqDto.getPhone());
        //3. Dto리턴
        return new UpdatePhoneRespDto(userPS);
    }
    
    @Transactional
    public UpdateEmailRespDto 이메일수정(UpdateEmailReqDto updateEmailReqDto, Long id) {
        //1. user가 본인인지 체크
        Optional<User> userOP = userRepository.findById(updateEmailReqDto.getId());
        if (!userOP.isPresent()) {
            userRepository.findById(updateEmailReqDto.getId())
                    .orElseThrow(() -> (new CustomApiException("해당유저가 존재하지 않습니다.", HttpStatus.BAD_REQUEST)));
        }
        //2. 이메일 수정
        User userPS = userOP.get();
        userPS.이메일수정(updateEmailReqDto.getEmail());
        //3. Dto리턴
        return new UpdateEmailRespDto(userPS);
    }

    @Transactional
    public UpdateStateRespDto 회원탈퇴(UpdateStateReqDto updateStateReqDto, Long id) {
        //1. user가 본인인지 체크
        Optional<User> userOP = userRepository.findById(updateStateReqDto.getId());
        if (!userOP.isPresent()) {
            userRepository.findById(updateStateReqDto.getId())
                    .orElseThrow(() -> (new CustomApiException("해당유저가 존재하지 않습니다.", HttpStatus.BAD_REQUEST)));
        }
        //2. user의 state를 '탈퇴' 로 바꾸기
        User userPS = userOP.get();
        userPS.회원탈퇴(updateStateReqDto.getState());
        return new UpdateStateRespDto(userPS);
    }


    @Transactional
    public void 회원영구삭제(Long id) {
        //1. ROLE이 admin이고
        //2. user의 state가 '탈퇴' 상태이면
        //3. 회원 영구삭제
        userRepository.deleteById(id);
    }
}
