package shop.ggamf.ggamf.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.ggamf.ggamf.config.auth.LoginUser;
import shop.ggamf.ggamf.config.enums.UserEnum;
import shop.ggamf.ggamf.dto.AdminReqDto.SaveGameReqDto;
import shop.ggamf.ggamf.dto.AdminReqDto.UpdateGameReqDto;
import shop.ggamf.ggamf.dto.AdminRespDto.SaveGameRespDto;
import shop.ggamf.ggamf.dto.AdminRespDto.UpdateGameRespDto;
import shop.ggamf.ggamf.dto.ResponseDto;
import shop.ggamf.ggamf.service.AdminService;

@RequestMapping("/s/api")
@RequiredArgsConstructor
@RestController
public class AdminApiController {

    private final AdminService adminService;

    @GetMapping("/admin/reportList")
    public ResponseEntity<?> findReportList(@AuthenticationPrincipal LoginUser loginUser) {
        if (!loginUser.getUser().getRole().equals(UserEnum.ADMIN)) {
            return new ResponseEntity<>(new ResponseDto<>("권한이 없습니다", null), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(new ResponseDto<>("신고목록 보기 성공", adminService.신고목록보기()), HttpStatus.OK);
    }

    @GetMapping("/admin/{reportId}/detailReport")
    public ResponseEntity<?> findDetailReport(@PathVariable Long reportId, @AuthenticationPrincipal LoginUser loginUser) {
        if (!loginUser.getUser().getRole().equals(UserEnum.ADMIN)) {
            return new ResponseEntity<>(new ResponseDto<>("권한이 없습니다", null), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(new ResponseDto<>("신고 상세내용 보기 성공", adminService.리포트상세내용보기(reportId)),
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
    public ResponseEntity<?> saveGame(@RequestBody SaveGameReqDto saveGameReqDto,
            @AuthenticationPrincipal LoginUser loginUser) {
        if (!loginUser.getUser().getRole().equals(UserEnum.ADMIN)) {
            return new ResponseEntity<>(new ResponseDto<>("권한이 없습니다", null), HttpStatus.FORBIDDEN);
        }
        SaveGameRespDto saveGameRespDto = adminService.게임추가하기(saveGameReqDto);
        return new ResponseEntity<>(new ResponseDto<>("게임 추가 성공", saveGameRespDto), HttpStatus.CREATED);
    }

    @PutMapping("/admin/{id}/updateGame")
    public ResponseEntity<?> updateGame(@PathVariable Long id, @RequestBody UpdateGameReqDto updateGameReqDto,
            @AuthenticationPrincipal LoginUser loginUser) {
        if (!loginUser.getUser().getRole().equals(UserEnum.ADMIN)) {
            return new ResponseEntity<>(new ResponseDto<>("권한이 없습니다", null), HttpStatus.FORBIDDEN);
        }
        updateGameReqDto.setId(id);
        UpdateGameRespDto updateGameRespDto = adminService.게임정보수정(updateGameReqDto);
        return new ResponseEntity<>(new ResponseDto<>("게임정보 수정 성공", updateGameRespDto), HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}/deleteGame")
    public ResponseEntity<?> deleteGame(@PathVariable Long id, @AuthenticationPrincipal LoginUser loginUser) {
        if (!loginUser.getUser().getRole().equals(UserEnum.ADMIN)) {
            return new ResponseEntity<>(new ResponseDto<>("권한이 없습니다", null), HttpStatus.FORBIDDEN);
        }
        adminService.게임삭제(id);
        return new ResponseEntity<>(new ResponseDto<>("게임 삭제 성공", null), HttpStatus.OK);
    }

    @GetMapping("/admin/gameList")
    public ResponseEntity<?> findGameList(@AuthenticationPrincipal LoginUser loginUser) {
        if (!loginUser.getUser().getRole().equals(UserEnum.ADMIN)) {
            return new ResponseEntity<>(new ResponseDto<>("권한이 없습니다", null), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(new ResponseDto<>("게임 목록 보기 성공", adminService.게임목록보기()), HttpStatus.OK);
    }

    @GetMapping("/admin/roomList")
    public ResponseEntity<?> findRoomList(@AuthenticationPrincipal LoginUser loginUser) {
        if (!loginUser.getUser().getRole().equals(UserEnum.ADMIN)) {
            return new ResponseEntity<>(new ResponseDto<>("권한이 없습니다", null), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(new ResponseDto<>("방 목록 보기 성공", adminService.방목록보기()), HttpStatus.OK);
    }
}
