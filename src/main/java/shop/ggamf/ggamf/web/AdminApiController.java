package shop.ggamf.ggamf.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.ggamf.ggamf.dto.ResponseDto;
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
}
