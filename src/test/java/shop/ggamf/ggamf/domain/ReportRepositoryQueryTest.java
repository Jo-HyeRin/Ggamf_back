package shop.ggamf.ggamf.domain;

import javax.persistence.EntityManager;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import shop.ggamf.ggamf.config.dummy.DummyEntity;
import shop.ggamf.ggamf.domain.reasonCode.ReasonCode;
import shop.ggamf.ggamf.domain.reasonCode.ReasonCodeRepository;
import shop.ggamf.ggamf.domain.report.DetailReportRespDto;
import shop.ggamf.ggamf.domain.report.Report;
import shop.ggamf.ggamf.domain.report.ReportRepository;
import shop.ggamf.ggamf.domain.report.ReportRepositoryQuery;
import shop.ggamf.ggamf.domain.report.ReportRespDto;
import shop.ggamf.ggamf.domain.user.User;
import shop.ggamf.ggamf.domain.user.UserRepository;

@Import(ReportRepositoryQuery.class)
@ActiveProfiles("test")
@DataJpaTest
public class ReportRepositoryQueryTest extends DummyEntity {

    @Autowired
    private EntityManager em;

    @Autowired
    private ReportRepositoryQuery reportRepositoryQuery;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReasonCodeRepository reasonCodeRepository;

    @BeforeEach
    public void setUp() {
        autoincrement_reset();
        dummy_init();
    }

    @Test
    public void findDetailReport_test() {
        // given
        Long id = 2L;
        // when
        DetailReportRespDto detailReportRespDto = reportRepositoryQuery.findDetailReport(id);
        System.out.println("detail : " + detailReportRespDto.getDetail());

        // then
        // Assertions.assertThat(detailReportRespDto.getDetail()).isEqualTo("이러저러한사유");
    }

    @Test
    public void findReportList_test4() {
        // given

        // when
        ReportRespDto reportRespDto = reportRepositoryQuery.findReportList().get(0);

        // then
        Assertions.assertThat(reportRespDto.getReason()).isEqualTo("잘못1");
    }

    private void dummy_init() {
        User admin = userRepository.save(newAdmin("admin", "01012345678"));
            User ssar = userRepository.save(newUser("ssar", "김쌀", "그만둬 베이가", "01011112222", "안녕하세요"));
            User cos = userRepository.save(newUser("cos", "김코스", "티모밴하면던짐", "01012123456", "티모밴하면던짐"));
            User lala = userRepository
                    .save(newUser("lala", "곽형수", "소나없는소나원챔", "01011234567", "듀오구해요"));
            User dada = userRepository.save(newUser("dada", "전창희", "CoolCat", "01012216789", "."));
            User kaka = userRepository
                    .save(newUser("kaka", "박진석", "벌써포기하는거냐", "01034567899", "오직노력"));
            User vovo = userRepository
                    .save(newUser("vovo", "이상지", "우리팀아힘좀내봐", "01021413568", "근성"));
            User toto = userRepository.save(newUser("toto", "송성훈", "행날", "01065984215", "안녕하세요"));
            User ohoh = userRepository.save(newUser("ohoh", "김혜석", "건축학과", "01060214502", "안녕하세요"));
            User yeye = userRepository
                    .save(newUser("yeye", "김장군", "아맞다퇴사", "01073214832", "항상 사직서를.."));
            User gogo = userRepository
                    .save(newUser("gogo", "최정웅", "냉장고등어", "01002367535", "주말에만 게임해요"));
            User romio = userRepository.save(newUser("romio", "서재균", "메카마루쉐", "01033094416", "마루쉐"));
            User jeje = userRepository
                    .save(newUser("jeje", "고상희", "Phiroth", "01065302408", "롤 랭겜 듀오구해요"));
            User money = userRepository
                    .save(newUser("money", "이승호", "Woodang", "01099814520", "저녁에 게임하실 분"));
            User terry = userRepository.save(newUser("terry", "이민서", "이거넴", "01011220242", "인생겜 찾음"));
            User wow = userRepository.save(newUser("wow", "권주안", "Awesomes", "01047286636", "안녕하세요"));
            User cash = userRepository.save(newUser("cash", "진하은", "햄버거피자", "01059453824", "떡볶이마라탕"));
            User power = userRepository
                    .save(newUser("power", "김소정", "망겜소생기원", "01078560214", "안녕하세요"));
            User house = userRepository.save(newUser("house", "임주영", "양날의검", "01020221219", "안녕하세요"));
            User nero20 = userRepository.save(newUser("nero20", "정유진", "포치타", "01091212202", "안녕하세요"));
            User poll = userRepository.save(newUser("poll", "최은아", "화목", "01034128972", "안녕하세요"));
            User user22 = userRepository
                    .save(newUser("tension", "이세연", "텐션유지가능", "01022048864", "안녕하세요"));
            User user23 = userRepository.save(newUser("alive", "최지원", "부활술사", "01068482848", "안녕하세요"));
            User user24 = userRepository.save(newUser("pepe", "홍승현", "페페", "01010011004", "귀엽지"));
            User 탑솔러그자체 = userRepository
                    .save(newUser("탑솔러그자체", "윤세정", "탑솔러그자체", "01094368540", "탑솔러"));
            User 아기사자 = userRepository.save(newUser("아기사자", "김비트", "아기사자","01033690889", "아기사자입니다"));
            User 둘리 = userRepository.save(newUser("둘리", "이둘리", "둘리","01022347557", "요리보고"));
            User 키보드부순당 = userRepository.save(newUser("키보드부순당", "박샷건", "키보드부순당", "01023498765", "조금만 잘하자 얘들아"));
            User 임요환짱 = userRepository.save(newUser("임요환짱", "임요환","임요환짱", "01011110111", "테란황제"));
            User 페이커팬 = userRepository.save(newUser("페이커팬", "이상혁", "페이커팬", "01008270313", "페이커 최고"));
            User 뜨뜨뜨뜨 = userRepository.save(newUser("뜨뜨뜨뜨", "이또또", "뜨뜨뜨뜨", "01077777866", "ㄸㄸㄸㄸ"));
            User 딜찍누 = userRepository.save(newUser("딜찍누", "김딜찍", "딜찍누", "01098897889", "공대원구합니다"));
            User 스피드레이서 = userRepository.save(newUser("스피드레이서", "김속도", "스피드레이서", "01015883061", "이길때됐음"));

        ReasonCode reason1 = reasonCodeRepository.save(newReason("잘못1"));
        ReasonCode reason2 = reasonCodeRepository.save(newReason("잘못2"));

        Report report1 = reportRepository.save(newReport(dada, kaka, reason1));
        Report report2 = reportRepository.save(newReport(cos, kaka, reason2));
    }

    private void autoincrement_reset() {
        this.em
                .createNativeQuery("ALTER TABLE users ALTER COLUMN `id` RESTART WITH 1")
                .executeUpdate();
    }
}
