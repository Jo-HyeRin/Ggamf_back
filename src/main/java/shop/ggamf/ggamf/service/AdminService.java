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
import shop.ggamf.ggamf.domain.gameCode.GameCode;
import shop.ggamf.ggamf.domain.gameCode.GameCodeRepository;
import shop.ggamf.ggamf.domain.report.DetailReportRespDto;
import shop.ggamf.ggamf.domain.report.ReportRepositoryQuery;
import shop.ggamf.ggamf.domain.report.ReportRespDto;
import shop.ggamf.ggamf.domain.statistics.GameMatchingResponseDto;
import shop.ggamf.ggamf.domain.statistics.StatisticsRepositoryQuery;
import shop.ggamf.ggamf.domain.user.User;
import shop.ggamf.ggamf.domain.user.UserRepository;
import shop.ggamf.ggamf.dto.AdminReqDto.SaveGameReqDto;
import shop.ggamf.ggamf.dto.AdminReqDto.UpdateGameReqDto;
import shop.ggamf.ggamf.dto.AdminRespDto.SaveGameRespDto;
import shop.ggamf.ggamf.dto.AdminRespDto.UpdateGameRespDto;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class AdminService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;
    private final GameCodeRepository gameCodeRepository;
    private final ReportRepositoryQuery reportRepositoryQuery;
    private final StatisticsRepositoryQuery statisticsRepositoryQuery;
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

    public List<GameMatchingResponseDto> 매칭통계목록보기() {
        List<GameMatchingResponseDto> gameMatchingResponseDto = statisticsRepositoryQuery.findGameMatchingStatistics();
        return gameMatchingResponseDto;
    }

    @Transactional
    public SaveGameRespDto 게임추가하기(SaveGameReqDto saveGameReqDto) {
        GameCode gameCodePS = gameCodeRepository.save(saveGameReqDto.toEntity());
        return new SaveGameRespDto(gameCodePS);
    }

    @Transactional
    public UpdateGameRespDto 게임정보수정(UpdateGameReqDto updateGameReqDto) {
        Optional<GameCode> gameCodeOP = gameCodeRepository.findById(updateGameReqDto.getId());
        if (!gameCodeOP.isPresent()) {
            gameCodeRepository.findById(updateGameReqDto.getId())
                    .orElseThrow(() -> (new CustomApiException("해당 게임이 존재하지 않습니다", HttpStatus.BAD_REQUEST)));
        }
        GameCode gameCodePS = gameCodeOP.get();
        gameCodePS.로고수정(updateGameReqDto.getLogo());
        gameCodePS.게임이름수정(updateGameReqDto.getGameName());

        return new UpdateGameRespDto(gameCodePS);
    }

    @Transactional
    public void 게임삭제(Long id) {
        Optional<GameCode> gameCodeOP = gameCodeRepository.findById(id);
        if (!gameCodeOP.isPresent()) {
            gameCodeRepository.findById(id)
                    .orElseThrow(() -> (new CustomApiException("해당 게임이 존재하지 않습니다", HttpStatus.BAD_REQUEST)));
        }
        gameCodeRepository.deleteById(id);
    }
}