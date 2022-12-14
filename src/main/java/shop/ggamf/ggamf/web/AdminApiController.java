package shop.ggamf.ggamf.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.ggamf.ggamf.config.auth.LoginUser;
import shop.ggamf.ggamf.config.enums.UserEnum;
import shop.ggamf.ggamf.dto.ResponseDto;
import shop.ggamf.ggamf.dto.AdminReqDto.SaveGameReqDto;
import shop.ggamf.ggamf.dto.AdminRespDto.SaveGameRespDto;
import shop.ggamf.ggamf.service.AdminService;

@RequestMapping("/s/api")
@RequiredArgsConstructor
@RestController
public class AdminApiController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final AdminService adminService;

    @GetMapping("/admin/{id}/reportList")
    public ResponseEntity<?> findReportList(@PathVariable Long id) {
        log.debug("디버그 : 여기는 탔지롱");
        return new ResponseEntity<>(new ResponseDto<>("신고목록 보기 성공", adminService.신고목록보기(id)), HttpStatus.OK);
    }

    @GetMapping("/admin/{reportId}/detailReport/{badUserId}")
    public ResponseEntity<?> findDetailReport(@PathVariable Long reportId, @PathVariable Long badUserId,
            @AuthenticationPrincipal LoginUser loginUser) {
        if (!loginUser.getUser().getRole().equals(UserEnum.ADMIN)) {
            return new ResponseEntity<>(new ResponseDto<>("권한이 없습니다", null), HttpStatus.FORBIDDEN);
        }
        log.debug("디버그 badUserId: " + badUserId);
        return new ResponseEntity<>(new ResponseDto<>("신고 상세내용 보기 성공", adminService.리포트상세내용보기(reportId, badUserId)),
                HttpStatus.OK);
    }

    @GetMapping("/admin/gameMatchingList")
    public ResponseEntity<?> findGameMatchingList(@AuthenticationPrincipal LoginUser loginUser) {
        if (!loginUser.getUser().getRole().equals(UserEnum.ADMIN)) {
            return new ResponseEntity<>(new ResponseDto<>("권한이 없습니다", null), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(new ResponseDto<>("게임 매칭 통계 리스트 불러오기 성공", adminService.매칭통계목록보기()), HttpStatus.OK);
    }

    @PostMapping("/admin/saveGame")
    public ResponseEntity<?> saveGame(@RequestBody SaveGameReqDto saveGameReqDto) {
        SaveGameRespDto saveGameRespDto = adminService.게임추가하기(saveGameReqDto);
        return new ResponseEntity<>(new ResponseDto<>("게임 추가 성공", saveGameRespDto), HttpStatus.CREATED);
    }
}
