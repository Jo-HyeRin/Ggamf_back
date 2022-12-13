package shop.ggamf.ggamf.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.ggamf.ggamf.config.auth.LoginUser;
import shop.ggamf.ggamf.config.exception.CustomApiException;
import shop.ggamf.ggamf.domain.report.DetailReportRespDto;
import shop.ggamf.ggamf.domain.report.ReportRepositoryQuery;
import shop.ggamf.ggamf.domain.report.ReportRespDto;
import shop.ggamf.ggamf.domain.user.User;
import shop.ggamf.ggamf.domain.user.UserRepository;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class AdminService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;
    private final ReportRepositoryQuery reportRepositoryQuery;
    private static LoginUser loginUser;

    public List<ReportRespDto> 신고목록보기(Long id) {
        Optional<User> userOP = userRepository.findById(id);
        log.debug("디버그 : username : " + userOP.get().getUsername());
        log.debug("디버그 : role : " + userOP.get().getRole());

        if (!userOP.isPresent()) {
            userRepository.findById(id)
                    .orElseThrow(() -> (new CustomApiException("해당유저가 존재하지 않습니다.", HttpStatus.BAD_REQUEST)));
        }
        List<ReportRespDto> reportRespDto = reportRepositoryQuery.findReportList();
        return reportRespDto;
    }

    public DetailReportRespDto 리포트상세내용보기(Long id, Long badUserId) {
        DetailReportRespDto detailReportRespDto = reportRepositoryQuery.findDetailReport(id, badUserId);
        return detailReportRespDto;
    }
}