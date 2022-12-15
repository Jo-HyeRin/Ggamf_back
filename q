[33mcommit e4de2cf24dd30527ac77e91600dd46a502765852[m[33m ([m[1;36mHEAD -> [m[1;32mparty-topic[m[33m)[m
Merge: 35fdd62 6c1e88c
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Thu Dec 15 14:19:40 2022 +0900

    pull

[33mcommit 6c1e88c311fbda63be8def4b79688d3d8fda0cef[m[33m ([m[1;31morigin/temp[m[33m, [m[1;31morigin/HEAD[m[33m)[m
Author: suhyeon <112357365+suucode@users.noreply.github.com>
Date:   Thu Dec 15 09:17:05 2022 +0900

    [임수현] JWT토큰에 uid 추가 (#48)
    
    * join 완료
    
    * join시 토큰생성이 안됨
    
    * login 실행시 토큰생성오류
    
    * login 완료
    
    * [조혜린] 파티방 생성 Test까지 완료
    
    [조혜린] 파티방 생성 Test까지 완료
    
    * UserStateEnum 생성
    
    * pull받기 전
    
    * service 수정
    
    * 자기소개 수정 완료
    
    * userService 중복체크제외 구현완료
    
    * update(사진 base64제외), 회원탈퇴 완료
    
    * user 가입/탈퇴 수정완료
    
    * User domain 수정
    
    * 유저상세보기 service 완료
    
    * pull 받기 전
    
    * userRepositoryQueryTest 완료
    
    * 유저 상세보기 기능완성
    
    * pull 받음
    
    * 유저 상세보기 완료
    
    * Junit test중
    
    * ReportRepositoryQueryTest 성공
    
    * 리포트 한건보기 쿼리 완료
    
    * userApiControllerTest 작성중
    
    * user join/update JUnit test 완료
    
    * user JUnit test 완료
    
    * JwtProcess 토큰 만료 예외처리 구현
    
    * 아이디 중복확인 기능 & JUnit test 완료
    
    * 회원가입 중복체크 & JUnit Test 완료
    
    * 회원가입 중복체크 메세지 수정
    
    * 토큰에 uid 추가
    
    Co-authored-by: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>

[33mcommit 8065c32cbdd6648f87c5658d490c0229a698cff4[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Thu Dec 15 04:34:07 2022 +0900

    [조혜린] BanUser → Banuser 폴더명, 파일명 변경
    
    * Rename RecommendBanUser.java to RecommendBanuser.java
    
    * Rename RecommendBanUserRepository.java to RecommendBanuserRepository.java
    
    * recommendBanUser -> recommendBanuser

[33mcommit 24a0e1bebe4bd4eeb35c8cb2ad4d6d0964b2fdb2[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Thu Dec 15 02:55:38 2022 +0900

    [조혜린] ggamf 관련 followId 받아오는 부분 friendId로 변경, 코드 수정
    
    * Ggamf 관련 followId 받아오는 부분 friendId로 변경 완료
    
    * followId -> friendId 적용 중 ggamf 관련 코드 수정
    
    * 더미데이터 추가 및 테스트 모두 정상확인, API 포스트맨 확인까지 완료
    
    * followrepository test 쿼리 추가
    
    * pull 작업 완료

[33mcommit e31e5bd57360f0042fb2070203a6365461044cc6[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Wed Dec 14 17:10:50 2022 +0900

    [조혜린] 추천겜프 삭제 적용, GgamfRespDto 프론트 요청에 따라 수정
    
    * 추천겜프삭제기능구현
    
    * 추천겜프삭제 junit test 완료
    
    * 추천겜프목록에 겜프삭제 반영, 겜프요청 시 겜프삭제 테이블 insert 반영
    
    * ggamfrespdto 응답 프론트 요청에 맞게 수정

[33mcommit cd316b982eb621ecead008c6ffe2fbc5c6ea8047[m
Author: suhyeon <112357365+suucode@users.noreply.github.com>
Date:   Wed Dec 14 15:36:21 2022 +0900

    [임수현] 게임 추가, 수정, 삭제 기능 & Junit 테스트 완료 (#44)
    
    * 게임 매칭 통계 보기 쿼리테스트 완료
    
    * 게임 매칭 통계 리스트 기능 & JUnit Test 완료
    
    * 게임 추가 기능 구현
    
    * 게임 추가 기능 & JUnit 테스트 완료
    
    * 게임 정보 수정 기능 & JUnit 테스트  완료
    
    * 게임 삭제 기능 & Junit 테스트 완료

[33mcommit 40a8a1236e0ec82fb1f266bd754ed47f976e32c2[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Wed Dec 14 15:32:21 2022 +0900

     [조혜린] 로그인 유저 권한 체크 AOP 적용 완료
    
    * AOP진행중
    
    * 로그인 유저 권한 체크 AOP 적용 완료

[33mcommit f9f71beb87cb4abb97d76b41f4d90d6151465636[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Wed Dec 14 14:41:06 2022 +0900

     [조혜린] 파티방 생성 화면 게임코드 전달 완료
    
    * 파티방 생성 화면 게임코드 전달 완료

[33mcommit 35fdd62af2130410c88367329c3190a122ad70c2[m[33m ([m[1;31morigin/party-topic[m[33m)[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Wed Dec 14 14:40:01 2022 +0900

    파티방 생성 화면 게임코드 전달 완료

[33mcommit 3529362674e79a9c9ef82e3b6cfa0e7694c83fce[m
Merge: d7b3e10 c61d442
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Wed Dec 14 14:28:53 2022 +0900

    Merge branch 'temp' of https://github.com/Jo-HyeRin/Ggamf_back into party-topic

[33mcommit c61d4424d85f973b0c9b9256af6e046819818ae7[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Wed Dec 14 14:02:16 2022 +0900

     [조혜린] Ggamf 관련 val 체크 완료
    
    * Ggamf 관련 validation check
    
    * ggamf 관련 val check 완료
    
    * ggamfreqdto, partyreqdto val check 완료

[33mcommit d7b3e1092df396dc6133d03d558728fd6833babe[m
Merge: 16235e6 c54fcec
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Wed Dec 14 12:42:11 2022 +0900

    Merge branch 'temp' of https://github.com/Jo-HyeRin/Ggamf_back into party-topic

[33mcommit c54fcecc32d142e5581d8223be907b86fd0bbb79[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Wed Dec 14 12:41:42 2022 +0900

    [조혜린] Party 관련 val 체크, 겜프요청 코드 수정, 응답 시 friendId → userId 통일
    
    * Party 관련 validation check
    
    * 겜프요청 코드 수정
    
    * ggamf 응답 friendId -> userId 통일

[33mcommit 16235e6c950c8a7e2c0a89dda2192208acaa49f7[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Wed Dec 14 12:38:37 2022 +0900

    ggamf 응답 friendId -> userId 통일

[33mcommit 3e9dfa89ea11dafc3f3b63fed83e2be5cdf1cc84[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Wed Dec 14 12:30:41 2022 +0900

    겜프요청 코드 수정

[33mcommit f52ed633a2a3f9450c87b930b0ca1a7c4d71e462[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Wed Dec 14 12:25:29 2022 +0900

    Party 관련 validation check

[33mcommit 2d7ec35751919b6dd4ca6a428fe5ec47ed7d3001[m
Merge: 28f1b3b 0ac9122
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Wed Dec 14 11:45:56 2022 +0900

    Merge branch 'temp' of https://github.com/Jo-HyeRin/Ggamf_back into party-topic

[33mcommit 0ac9122744fe9e4e429101187ac238cfc9444f34[m
Author: suhyeon <112357365+suucode@users.noreply.github.com>
Date:   Wed Dec 14 11:38:08 2022 +0900

    [임수현] 회원 가입 시 중복 체크 확인 메세지 수정 (#38)
    
    * join 완료
    
    * join시 토큰생성이 안됨
    
    * login 실행시 토큰생성오류
    
    * login 완료
    
    * [조혜린] 파티방 생성 Test까지 완료
    
    [조혜린] 파티방 생성 Test까지 완료
    
    * UserStateEnum 생성
    
    * pull받기 전
    
    * service 수정
    
    * 자기소개 수정 완료
    
    * userService 중복체크제외 구현완료
    
    * update(사진 base64제외), 회원탈퇴 완료
    
    * user 가입/탈퇴 수정완료
    
    * User domain 수정
    
    * 유저상세보기 service 완료
    
    * pull 받기 전
    
    * userRepositoryQueryTest 완료
    
    * 유저 상세보기 기능완성
    
    * pull 받음
    
    * 유저 상세보기 완료
    
    * Junit test중
    
    * ReportRepositoryQueryTest 성공
    
    * 리포트 한건보기 쿼리 완료
    
    * userApiControllerTest 작성중
    
    * user join/update JUnit test 완료
    
    * user JUnit test 완료
    
    * JwtProcess 토큰 만료 예외처리 구현
    
    * 아이디 중복확인 기능 & JUnit test 완료
    
    * 회원가입 중복체크 & JUnit Test 완료
    
    * 회원가입 중복체크 메세지 수정
    
    Co-authored-by: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>

[33mcommit c6699a74c2b0063cfd29706c80fe58cd400b318c[m
Author: suhyeon <112357365+suucode@users.noreply.github.com>
Date:   Wed Dec 14 11:14:04 2022 +0900

    [임수현] 회원 가입 시 중복 체크 기능 & JUnit Test 완료 (#37)
    
    * join 완료
    
    * join시 토큰생성이 안됨
    
    * login 실행시 토큰생성오류
    
    * login 완료
    
    * [조혜린] 파티방 생성 Test까지 완료
    
    [조혜린] 파티방 생성 Test까지 완료
    
    * UserStateEnum 생성
    
    * pull받기 전
    
    * service 수정
    
    * 자기소개 수정 완료
    
    * userService 중복체크제외 구현완료
    
    * update(사진 base64제외), 회원탈퇴 완료
    
    * user 가입/탈퇴 수정완료
    
    * User domain 수정
    
    * 유저상세보기 service 완료
    
    * pull 받기 전
    
    * userRepositoryQueryTest 완료
    
    * 유저 상세보기 기능완성
    
    * pull 받음
    
    * 유저 상세보기 완료
    
    * Junit test중
    
    * ReportRepositoryQueryTest 성공
    
    * 리포트 한건보기 쿼리 완료
    
    * userApiControllerTest 작성중
    
    * user join/update JUnit test 완료
    
    * user JUnit test 완료
    
    * JwtProcess 토큰 만료 예외처리 구현
    
    * 아이디 중복확인 기능 & JUnit test 완료
    
    * 회원가입 중복체크 & JUnit Test 완료
    
    Co-authored-by: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>

[33mcommit 28f1b3b37bea77f0ad8ed2dabe6aafe51adc7d94[m
Merge: 6be5eba 0383a5d
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Wed Dec 14 10:47:41 2022 +0900

    Merge branch 'temp' of https://github.com/Jo-HyeRin/Ggamf_back into party-topic

[33mcommit 0383a5dd391e98faa7a6c5ae4c4aba39b7c8fa75[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Wed Dec 14 10:47:18 2022 +0900

     [조혜린] Enter, Follow, Room Repository 작성 쿼리 Junit Test 완료
    
    * EnterRepository 작성 쿼리 junit test 완료
    
    * FollowRepository 작성 쿼리 junit test 완료
    
    * RoomRepository 작성 쿼리 junit test 완료

[33mcommit 6be5eba01e542f6f9d8fda48ddee62fa01b085f5[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Wed Dec 14 10:45:04 2022 +0900

    RoomRepository 작성쿼리 junit test 완료

[33mcommit 19e4b6c3c16dad8f0539de173dc3780720709820[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Wed Dec 14 09:52:49 2022 +0900

    FollowRepository 작성 쿼리 junit test 완료

[33mcommit b9a36d32c6f532e0e99a95575af59cc134b7d6a0[m
Author: Hyerinism <sayhyelin12@naver.com>
Date:   Wed Dec 14 01:45:44 2022 +0900

    EnterRepository 작성 쿼리 junit test 완료

[33mcommit d3a929394040d37bcc51ecc60314b2b52ed26c0d[m
Merge: 33c0bb4 6b9d2b7
Author: Hyerinism <sayhyelin12@naver.com>
Date:   Wed Dec 14 00:33:47 2022 +0900

    Merge branch 'temp' of https://github.com/Jo-HyeRin/Ggamf_back into party-topic

[33mcommit 6b9d2b7fe10bd2cfd1bf13519cd532c823296485[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Wed Dec 14 00:32:06 2022 +0900

     [조혜린] 페이징(전체파티방목록보기) & Junit Test 완료
    
    * 전체파티방목록보기 페이징 적용 완료
    
    * 전체파티방목록보기 페이징 junit test 완료

[33mcommit 33c0bb42350f124cf4f95e6d6438de2139741227[m
Merge: 48eea33 f8daa7a
Author: Hyerinism <sayhyelin12@naver.com>
Date:   Wed Dec 14 00:30:45 2022 +0900

    temp pull 완료

[33mcommit 48eea335ccc0fba295b60c542c38c51f3e37b89a[m
Author: Hyerinism <sayhyelin12@naver.com>
Date:   Wed Dec 14 00:23:44 2022 +0900

    전체파티방목록보기 페이징 junit test 완료

[33mcommit 142804f74be5aca1f725caadb84847b95cba0783[m
Author: Hyerinism <sayhyelin12@naver.com>
Date:   Wed Dec 14 00:00:49 2022 +0900

    전체파티방목록보기 페이징 적용 완료

[33mcommit f8daa7aee33bd18493ba0f2697e1cbb580ac1851[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Tue Dec 13 23:46:50 2022 +0900

     [조혜린] 게임코드별 전체 파티방 목록보기 & Junit Test 완료
    
    * 게임코드별 목록보기 구현중
    
    * 게임코드별파티방목록보기 junit test 완료

[33mcommit 101c916ab08ca94786c83edc1987b7fff3eade59[m
Author: Hyerinism <sayhyelin12@naver.com>
Date:   Tue Dec 13 23:45:17 2022 +0900

    게임코드별파티방목록보기 junit test 완료

[33mcommit 6dc7644be52f642407776f492a66ac826a704f00[m
Merge: a0fcf2b f1cb66d
Author: Hyerinism <sayhyelin12@naver.com>
Date:   Tue Dec 13 23:40:06 2022 +0900

    게임코드별 파티방 목록보기 완료

[33mcommit a0fcf2b4eb06f5973a54175e4e2f5044839c0947[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Tue Dec 13 17:58:14 2022 +0900

    게임코드별 목록보기 구현중

[33mcommit f1cb66d555c0ac987b400208c5f8e8c7b9d73157[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Tue Dec 13 16:39:38 2022 +0900

     [조혜린] 검색(전체파티방목록보기) 적용 & Junit Test 완료
    
    * 더미데이터 추가
    
    * 전체파티방목록보기 검색기능 추가
    
    * 검색기능 junit test 완료

[33mcommit ae08c0a9120385f0394d18ed0a59302f91fae7f4[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Tue Dec 13 16:37:59 2022 +0900

    검색기능 junit test 완료

[33mcommit 87e1d0764f875359d8de06d1d5cf648441d3d996[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Tue Dec 13 16:31:55 2022 +0900

    전체파티방목록보기 검색기능 추가

[33mcommit 811803cc7474f503e1679a62c2a7c872e43463b8[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Tue Dec 13 16:15:38 2022 +0900

    더미데이터 추가

[33mcommit e7b9766839dd08a5071c8210579bfdb2c83379d1[m
Merge: 84540ab 7faeb2f
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Tue Dec 13 16:09:14 2022 +0900

    temp pull 완료

[33mcommit 7faeb2f2acb0e324daebd89393f3d652c1d1e7f7[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Tue Dec 13 15:37:58 2022 +0900

    [조혜린] 받은 겜프요청 목록보기 & Junit Test 완료
    
    * 받은겜프요청 목록보기 완료
    
    * 받은겜프요청 목록보기 junit test 완료

[33mcommit e144a57c535dfc67585ea0d5b503f76c043c54af[m
Author: suhyeon <112357365+suucode@users.noreply.github.com>
Date:   Tue Dec 13 15:16:34 2022 +0900

    [임수현] 신고 목록 보기, 신고 상세 보기 기능 & JUnit Test 완료  (#31)
    
    * join 완료
    
    * join시 토큰생성이 안됨
    
    * login 실행시 토큰생성오류
    
    * login 완료
    
    * [조혜린] 파티방 생성 Test까지 완료
    
    [조혜린] 파티방 생성 Test까지 완료
    
    * UserStateEnum 생성
    
    * pull받기 전
    
    * service 수정
    
    * 자기소개 수정 완료
    
    * userService 중복체크제외 구현완료
    
    * update(사진 base64제외), 회원탈퇴 완료
    
    * user 가입/탈퇴 수정완료
    
    * User domain 수정
    
    * 유저상세보기 service 완료
    
    * pull 받기 전
    
    * userRepositoryQueryTest 완료
    
    * 유저 상세보기 기능완성
    
    * pull 받음
    
    * 유저 상세보기 완료
    
    * Junit test중
    
    * ReportRepositoryQueryTest 성공
    
    * 리포트 한건보기 쿼리 완료
    
    * userApiControllerTest 작성중
    
    * user join/update JUnit test 완료
    
    * user JUnit test 완료
    
    * 신고목록보기 완료
    
    * 신고 상세보기 완료&JunitTest완료
    
    Co-authored-by: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>

[33mcommit e7183c1049248315e855244fe03e193a636ad536[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Tue Dec 13 15:01:28 2022 +0900

    [조혜린] 보낸 겜프요청 목록보기 & Junit Test 완료
    
    * 보낸 겜프 요청 보기 완료, junit test 완료

[33mcommit 84540ab9d5dbf6015f18a3a65c328e1f43ef2579[m
Merge: 6a01399 1aaeb7f
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Tue Dec 13 14:56:16 2022 +0900

    Merge branch 'temp' of https://github.com/Jo-HyeRin/Ggamf_back into party-topic

[33mcommit 1aaeb7faaf8b5635ea8b931e8ce7647844bdfa2a[m
Author: suhyeon <112357365+suucode@users.noreply.github.com>
Date:   Tue Dec 13 14:54:38 2022 +0900

    [임수현] 로그인, 회원CRUD & JUnit 완료  (#28)
    
    * join 완료
    
    * join시 토큰생성이 안됨
    
    * login 실행시 토큰생성오류
    
    * login 완료
    
    * [조혜린] 파티방 생성 Test까지 완료
    
    [조혜린] 파티방 생성 Test까지 완료
    
    * UserStateEnum 생성
    
    * pull받기 전
    
    * service 수정
    
    * 자기소개 수정 완료
    
    * userService 중복체크제외 구현완료
    
    * update(사진 base64제외), 회원탈퇴 완료
    
    * user 가입/탈퇴 수정완료
    
    * User domain 수정
    
    * 유저상세보기 service 완료
    
    * pull 받기 전
    
    * userRepositoryQueryTest 완료
    
    * 유저 상세보기 기능완성
    
    * pull 받음
    
    * 유저 상세보기 완료
    
    * Junit test중
    
    * ReportRepositoryQueryTest 성공
    
    * 리포트 한건보기 쿼리 완료
    
    * userApiControllerTest 작성중
    
    * user join/update JUnit test 완료
    
    * user JUnit test 완료
    
    Co-authored-by: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>

[33mcommit 6a0139992a173c5ebc2fcc129f96a498e84f350a[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Tue Dec 13 14:52:58 2022 +0900

    보낸 겜프 요청 보기 완료, junit test 완료

[33mcommit d2ca6faa8b2a3c1b498aeead9f8b90d1ec32aa9c[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Tue Dec 13 14:24:45 2022 +0900

     [조혜린] 파티원 추방 코드 수정 및 테스트 정상 작동 확인
    
    * 파티원 추방 수정, 테스트 정상 작동 확인

[33mcommit 490aeab2401b38bddbe85d7fe270914115b2f59b[m
Merge: 4ddf04f eea1c9a
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Tue Dec 13 14:23:47 2022 +0900

    pull 작업 완료

[33mcommit 4ddf04fba7e090839139634f3b3d4f1a26f9bda3[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Tue Dec 13 14:21:54 2022 +0900

    파티원 추방 수정, 테스트 정상 작동 확인

[33mcommit eea1c9aa75149a9caa148724f7cfc6d009b67bbf[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Mon Dec 12 22:21:11 2022 +0900

     [조혜린] Party 관련 코드 정리 및 유효성 검사 적용
    
    * party 관련 api 문서 작성 중
    
    * party관련기능,테스트 완료, 유효성검사 진행중
    
    * party 유효성 검사 적용

[33mcommit 68b62cb397ee37ff00c988749f9d04d800f71ca9[m
Author: Hyerinism <sayhyelin12@naver.com>
Date:   Mon Dec 12 22:19:46 2022 +0900

    party 유효성 검사 적용

[33mcommit 3a195362c355d2b781e28357b7f712c1e8067c17[m
Author: Hyerinism <sayhyelin12@naver.com>
Date:   Mon Dec 12 22:03:24 2022 +0900

    party관련기능,테스트 완료, 유효성검사 진행중

[33mcommit 55f88c57e7532d46611479ffb9963ff376d3f5eb[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Mon Dec 12 17:56:40 2022 +0900

    party 관련 api 문서 작성 중

[33mcommit 0c7b3e7d4956a07d84cc228ab095bdf459d62096[m
Merge: b3d9a24 6fa8718
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Mon Dec 12 17:23:01 2022 +0900

    Merge branch 'temp' of https://github.com/Jo-HyeRin/Ggamf_back into party-topic

[33mcommit 6fa8718b3c358f328e824973555b2288f00c104d[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Mon Dec 12 17:22:07 2022 +0900

     [조혜린] Ggamf 관련 코드 정리 완료
    
    * 더미데이터 정리, GgamfController 관련 DTO, Service 수정
    
    * Ggamf 기능, 테스트 완료

[33mcommit 1572641cbacea210636c86af4ce82cc1b2f61792[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Mon Dec 12 14:12:32 2022 +0900

    [조혜린] 추천겜프목록보기 & Junit Test 완료
    
    * 추천겜프목록보기 완료, junit test 작성중
    
    * 파티방 종료 수정 완료
    
    * 추천친구목록보기 다 삭제, 다시 시작
    
    * 추천친구목록보기 진행중
    
    * 추천친구목록보기 완료 친구정보, 중복값 제거 고민
    
    * Follow 엔티티 수정으로 전체 코드 수정 필요
    
    * 추천겜프목록보기 테스트까지 완료, 중복제거 기존친구제거 고민중
    
    * 전체 정상 확인 완료, 수정 시작
    
    * 추천친구목록보기 완료
    
    * 추천친구목록보기 테스트까지 완료
    
    * 추천친구목록보기 코드 리팩토링 완료

[33mcommit b3d9a247413381cb919e88b52daabd12058cfb66[m
Merge: 47070bc c8da5eb
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Fri Dec 9 15:35:32 2022 +0900

    Merge branch 'temp' of https://github.com/Jo-HyeRin/Ggamf_back into party-topic

[33mcommit c8da5ebfa1f878139eb7e4f8b78b8e5acb41d413[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Fri Dec 9 15:35:14 2022 +0900

    [조혜린] 파티방종료수정, 전체 주소 수정, AuditingTime수정, 요청유저와 로그인유저 일치 검사 완료
    
    * 파티방 종료 수정 완료
    
    * AuditingTime 수정 완료
    
    * 주소수정, 요청유저와 로그인유저 일치 여부 검사 완료

[33mcommit 47070bcb612898e4a069516c37e1b7aa462f6409[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Fri Dec 9 15:33:00 2022 +0900

    주소수정, 요청유저와 로그인유저 일치 여부 검사 완료

[33mcommit 197f06619d631e07afc41f343466cc0676858b07[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Fri Dec 9 15:15:08 2022 +0900

    AuditingTime 수정 완료

[33mcommit 8fdd20b4156884cc34580d81ec48c27c08379684[m
Merge: 4139865 e9e96ab
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Fri Dec 9 12:48:45 2022 +0900

    Merge branch 'ggamf-topic' of https://github.com/Jo-HyeRin/Ggamf_back into party-topic

[33mcommit 41398656c0507e43faeeb194792b273a83924f63[m
Merge: 002408a 912f025
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Fri Dec 9 12:48:38 2022 +0900

    pull temp

[33mcommit e9e96ab97d964bddf82ca847ef1b27d335bafd36[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Fri Dec 9 12:28:49 2022 +0900

    파티방 종료 수정 완료

[33mcommit 602fec749f97aa5f7b243ca667d0f8543d29125e[m
Merge: 6878994 912f025
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Fri Dec 9 11:19:12 2022 +0900

    Merge branch 'temp' of https://github.com/Jo-HyeRin/Ggamf_back into ggamf-topic

[33mcommit 912f0254c856470f7283717c820142fb23e6ddd2[m
Author: suhyeon <112357365+suucode@users.noreply.github.com>
Date:   Fri Dec 9 09:23:04 2022 +0900

    [임수현] 회원 상세보기 완료 (#22)
    
    * join 완료
    
    * join시 토큰생성이 안됨
    
    * login 실행시 토큰생성오류
    
    * login 완료
    
    * [조혜린] 파티방 생성 Test까지 완료
    
    [조혜린] 파티방 생성 Test까지 완료
    
    * UserStateEnum 생성
    
    * pull받기 전
    
    * service 수정
    
    * 자기소개 수정 완료
    
    * userService 중복체크제외 구현완료
    
    * update(사진 base64제외), 회원탈퇴 완료
    
    * user 가입/탈퇴 수정완료
    
    * User domain 수정
    
    * 유저상세보기 service 완료
    
    * pull 받기 전
    
    * userRepositoryQueryTest 완료
    
    * 유저 상세보기 기능완성
    
    * pull 받음
    
    * 유저 상세보기 완료
    
    Co-authored-by: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>

[33mcommit 6878994962cffd1159c435862bef4244a0b0d208[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Thu Dec 8 18:31:12 2022 +0900

    추천겜프목록보기 완료, junit test 작성중

[33mcommit 58144eb6fea0ab9842bf8935f288da34d45342d7[m
Merge: c50c835 b758b17
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Thu Dec 8 15:14:04 2022 +0900

    Merge branch 'temp' of https://github.com/Jo-HyeRin/Ggamf_back into ggamf-topic

[33mcommit b758b175debd4d7fc26d8fa48c2bf123943d1ba9[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Thu Dec 8 14:56:24 2022 +0900

     [조혜린] 겜프 목록보기 & Junit Test 완료
    
    * 겜프목록보기 완료
    
    * 겜프목록보기 junit test 완료

[33mcommit c50c835e8840bc2ce4911c7efab3009bec549d7c[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Thu Dec 8 14:55:19 2022 +0900

    겜프목록보기 junit test 완료

[33mcommit 6247b0f461c8262c31fad9e1b85e70929a56a5c6[m
Merge: 2503134 c97dbfa
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Thu Dec 8 14:48:57 2022 +0900

    temp pull

[33mcommit 2503134f3736f808d0f56f1ec6a74f518bb568be[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Thu Dec 8 14:46:29 2022 +0900

    겜프목록보기 완료

[33mcommit c97dbfad5dadaea9492395582e45ea6338a778b2[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Thu Dec 8 14:13:59 2022 +0900

     [조혜린] 겜프신고 & Junit Test 완료
    
    * 겜프신고 완료
    
    * 겜프신고 junit test 완료

[33mcommit d07b235b58243303d0f8b6781db69d165544b144[m
Merge: 1d29dd8 1b1ea28
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Thu Dec 8 14:13:03 2022 +0900

    겜프신고 junit test 완료

[33mcommit 1d29dd8abd012bc1c8c80e509ba8a9c8c39a4999[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Thu Dec 8 14:02:53 2022 +0900

    겜프신고 완료

[33mcommit 1b1ea280a1a7ade121c74eb13e7a32aa7e0b8e66[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Thu Dec 8 12:19:26 2022 +0900

     [조혜린] 겜프요청취소 & Junit Test 완료
    
    * 겜프요청취소 완료
    
    * 겜프요청취소 junit test 완료

[33mcommit ca8b186be6166756426ff73c57121943e2c91f26[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Thu Dec 8 12:18:30 2022 +0900

    겜프요청취소 junit test 완료

[33mcommit 25feb166a3e16b0d6a2554975844c7f4cc6df127[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Thu Dec 8 12:16:07 2022 +0900

    겜프요청취소 완료

[33mcommit 4f26144f7274e1f99d705e9b9323f10e770b9a01[m
Merge: 5543a99 1bc8320
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Thu Dec 8 12:10:17 2022 +0900

    Merge branch 'temp' of https://github.com/Jo-HyeRin/Ggamf_back into ggamf-topic

[33mcommit 1bc8320ffee54c59917b13761babdfc88a895b36[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Thu Dec 8 11:45:41 2022 +0900

     [조혜린] 겜프 거절 & Junit Test 완료
    
    * 겜프 거절 완료
    
    * 겜프거절 junit test 완료

[33mcommit 5543a994cd31b08f1f34e7a3f2aef125464190b7[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Thu Dec 8 11:44:54 2022 +0900

    겜프거절 junit test 완료

[33mcommit 0d422c34aea8223c8ff5b04cdbaf0a76b8a2100f[m
Merge: e4ff923 fff86f0
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Thu Dec 8 11:43:10 2022 +0900

    ggamf-topic pull

[33mcommit e4ff92382de0b880eeca0112485dce499bf814ba[m
Merge: 7200020 652ed50
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Thu Dec 8 11:40:29 2022 +0900

    temp pull

[33mcommit 72000209357837d939140cfefb8cffd7249c61a9[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Thu Dec 8 11:33:28 2022 +0900

    겜프 거절 완료

[33mcommit 652ed50978673becdfe8d7a8ef389de9325e785e[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Thu Dec 8 11:17:27 2022 +0900

     [조혜린] 겜프 삭제 & Junit Test 완료
    
    * 겜프삭제 완료
    
    * 겜프삭제 junit test 완료

[33mcommit fff86f06f03680ac714b0f50fcbf3e4498c705d3[m
Merge: b297752 0e05e28
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Thu Dec 8 11:17:13 2022 +0900

    Merge branch 'temp' into ggamf-topic

[33mcommit b29775249a419ea56042bfaebbbfda528a24c8b4[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Thu Dec 8 11:15:01 2022 +0900

    겜프삭제 junit test 완료

[33mcommit 8757105262412792cb6056c8533ffa260e552244[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Thu Dec 8 10:55:16 2022 +0900

    겜프삭제 완료

[33mcommit 0e05e28666d407c85a89d490b0798b61650cba66[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Wed Dec 7 17:43:40 2022 +0900

    [조혜린] 겜프 수락 & Junit Test 완료 (#16)
    
    * 겜프수락 완료
    
    * 겜프수락 junit test 완료

[33mcommit ba85176e2e8c3da8681ce141f65366d748619522[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Wed Dec 7 17:42:39 2022 +0900

    겜프수락 junit test 완료

[33mcommit 056ae760477baa33ca4a1d676a2de0d819dcf7a6[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Wed Dec 7 17:28:05 2022 +0900

    겜프수락 완료

[33mcommit 3e940b356f275eabf92d14c762c9cdac3c1ae36f[m
Merge: 9708420 4de7fe8
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Wed Dec 7 17:06:40 2022 +0900

    Merge branch 'temp' of https://github.com/Jo-HyeRin/Ggamf_back into ggamf-topic

[33mcommit 4de7fe89696ab3a2f1c6edcd74e73f49284ebd2a[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Wed Dec 7 17:06:06 2022 +0900

    [조혜린] 겜프 요청 & Junit Test 완료
    
    * 겜프요청 완료
    
    * 겜프요청 junit test 완료
    
    * 겜프요청 junit test 수정 완료

[33mcommit 9708420695b84074e4d6c90c457652e551559b97[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Wed Dec 7 16:54:32 2022 +0900

    겜프요청 junit test 수정 완료

[33mcommit 1d26523e5660aac88fc3f0453d6efcf135fcd84d[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Wed Dec 7 16:52:55 2022 +0900

    겜프요청 junit test 완료

[33mcommit 34890ce62b3578ac936daffaa6473eaf85116521[m
Merge: 002408a 355491f
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Wed Dec 7 14:35:49 2022 +0900

    겜프요청 완료

[33mcommit 002408aa65d49fa5106173a1236898082975f577[m
Merge: 63ae3f2 42590a8
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Wed Dec 7 11:25:50 2022 +0900

    temp pull

[33mcommit 42590a8f34184678e59820eae65b8abf572cd59f[m
Author: suhyeon <112357365+suucode@users.noreply.github.com>
Date:   Wed Dec 7 11:20:24 2022 +0900

    [임수현] User domain 수정 및 회원정보 수정/탈퇴 완료 (#14)
    
    * join 완료
    
    * join시 토큰생성이 안됨
    
    * login 실행시 토큰생성오류
    
    * login 완료
    
    * [조혜린] 파티방 생성 Test까지 완료
    
    [조혜린] 파티방 생성 Test까지 완료
    
    * UserStateEnum 생성
    
    * pull받기 전
    
    * service 수정
    
    * 자기소개 수정 완료
    
    * userService 중복체크제외 구현완료
    
    * update(사진 base64제외), 회원탈퇴 완료
    
    * user 가입/탈퇴 수정완료
    
    * User domain 수정
    
    Co-authored-by: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>

[33mcommit 55f05e675d3f2c1ac3d8c88692cd3443ff70d99d[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Wed Dec 7 11:03:18 2022 +0900

    [조혜린] 참가중인 파티방 목록 보기 & Junit Test 완료 (#13)
    
    * 참가중인파티방목록보기 완료
    
    * EnterRepository 쿼리 수정, 참가중인파티방목록보기 JunitTest완료

[33mcommit 355491fa4b0665fc939d5167430a7e87ee2bc82f[m
Merge: 63ae3f2 e71ad43
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Wed Dec 7 11:03:03 2022 +0900

    Merge branch 'temp' into party-topic

[33mcommit 63ae3f231d436cc7e8face0b3c0a6233bff96ab1[m
Merge: 2ff5f7c 41c90bf
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Wed Dec 7 11:01:25 2022 +0900

    쿼리 수정, 참가중인파티방목록보기 Junit Test 완료

[33mcommit 2ff5f7cbe66fc848a086b69b46763d6c306874f6[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Wed Dec 7 10:59:39 2022 +0900

    EnterRepository 쿼리 수정, 참가중인파티방목록보기 JunitTest완료

[33mcommit 5c0203e7842f2a5756724c31b588430cb2a9901a[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Wed Dec 7 10:55:37 2022 +0900

    참가중인파티방목록보기 완료

[33mcommit e71ad43a78b5e631c2eb56ee174938f726d4094b[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Wed Dec 7 10:25:35 2022 +0900

    [조혜린] 전체 파티 목록 보기 & Junit Test 완료
    
    * 전체파티방목록보기 완료
    
    * 전체파티방목록보기 junit test 완료

[33mcommit 41c90bf350e68da7df95cb2379c0f8714cf94cb4[m
Merge: 317d8cd 9a6c907
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Wed Dec 7 10:25:21 2022 +0900

    Merge branch 'temp' into party-topic

[33mcommit 317d8cdc58b7a371e0fee776c10f13aa00f7e649[m
Merge: 59aecee 404bc25
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Wed Dec 7 10:23:28 2022 +0900

    pull 작업 완료

[33mcommit 59aecee7d5e5e8be15e698195827bfc1e64d5a78[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Wed Dec 7 10:21:31 2022 +0900

    전체파티방목록보기 junit test 완료

[33mcommit de8aac65081d57f17babd52d43a163ae21cf72af[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Wed Dec 7 10:18:36 2022 +0900

    전체파티방목록보기 완료

[33mcommit 9a6c90734f2884d86bc000dd847632f6a2efa8dd[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Wed Dec 7 10:06:36 2022 +0900

     [조혜린] 파티방 상세 보기 & Junit Test 완료
    
    * 파티방 상세보기 완료
    
    * 파티방 상세보기 Junit Test 완료

[33mcommit 404bc2504e175f923770484e2ac4444bbf054a55[m
Merge: 0cad767 5e932ae
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Wed Dec 7 10:06:21 2022 +0900

    Merge branch 'temp' into party-topic

[33mcommit 0cad76780504aa49920465b29dcc3b69ab694555[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Wed Dec 7 10:03:49 2022 +0900

    파티방 상세보기 Junit Test 완료

[33mcommit a3a1a3df34a33b64cd64c0d071768d70cab22c52[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Wed Dec 7 09:57:34 2022 +0900

    파티방 상세보기 완료

[33mcommit 5e932ae45505d29923ae6d43a3ac9048f5abbe12[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Wed Dec 7 09:45:49 2022 +0900

     [조혜린] 나의 모집 파티 목록 보기 & Junit Test 완료
    
    * 나의모집파티목록보기 완료
    
    * 나의모집파티목록보기 junit test 완료

[33mcommit 329a39ec0c361b5cc14eb614af689db118f07d46[m
Merge: 8b94651 970d7d2
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Wed Dec 7 09:45:34 2022 +0900

    Merge branch 'temp' into party-topic

[33mcommit 8b946512530f55e8b55831fd4b44e32751912af9[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Wed Dec 7 09:37:29 2022 +0900

    나의모집파티목록보기 junit test 완료

[33mcommit d19292f2c783e42065d7a684df1741d3ec430eff[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Wed Dec 7 09:25:06 2022 +0900

    나의모집파티목록보기 완료

[33mcommit 970d7d2acd5bb28d36cf8a98bf8b17da444f1946[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Tue Dec 6 17:59:58 2022 +0900

    [조혜린] 파티원 추방 & Junit Test 완료 (#9)
    
    * 파티원 추방 완료
    
    * 파티원 추방 junit test 완료

[33mcommit bab1ac971a0273109e442d0275aed6567b815040[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Tue Dec 6 17:50:11 2022 +0900

    파티원 추방 junit test 완료

[33mcommit 8806e3dc448d5641946ac3075810b50d885d397c[m
Merge: 03bfa83 a53deb7
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Tue Dec 6 17:32:27 2022 +0900

    git pull 작업 완료

[33mcommit 03bfa83c61f14aa1bf264dd55e673afc3a5bbae4[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Tue Dec 6 17:24:57 2022 +0900

    파티원 추방 완료

[33mcommit a53deb7774354c599e971a3ea2e82dd2de1e43e1[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Tue Dec 6 16:56:19 2022 +0900

    [조혜린] 파티방 종료 & Junit Test 완료
    
    * 파티방 종료하기 완료
    
    * 파티방 종료 junit test 완료
    
    * PartyApiControllerTest setUP 데이터 수정

[33mcommit 326c52a84a95d80cfb07d9c8927fa3ec97781f06[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Tue Dec 6 16:43:27 2022 +0900

    PartyApiControllerTest setUP 데이터 수정

[33mcommit b0b745cae608ce298a9edeb547d2f9daceb4db73[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Tue Dec 6 16:29:14 2022 +0900

    파티방 종료 junit test 완료

[33mcommit 913cefbbe1378ef2f1e3d30a061b4cb4803c07ac[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Tue Dec 6 16:12:24 2022 +0900

    파티방 종료하기 완료

[33mcommit a0ad6260dd2319b9fe019d2bd6c5fd1b7b8a3deb[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Tue Dec 6 15:43:09 2022 +0900

     [조혜린] 파티방 나가기 & Junit Test 완료
    
    * 파티방나가기(본인) 완료
    
    * 파티방나가기(본인) junit test 완료

[33mcommit 6373c346c56562cd60aecb80dcd456920643c8b2[m
Merge: 1346346 c05621f
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Tue Dec 6 15:34:07 2022 +0900

    Merge branch 'party-topic' of https://github.com/Jo-HyeRin/Ggamf_back into party-topic

[33mcommit 1346346d866084618f1fbfbce13b6165ef25659c[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Tue Dec 6 15:28:36 2022 +0900

    파티방나가기(본인) junit test 완료

[33mcommit b88d70a6665c9ee8b1acdbcd8c63b9d32da4c072[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Tue Dec 6 15:10:22 2022 +0900

    파티방나가기(본인) 완료

[33mcommit d668e96efbdf9743d53947e882bd4468213fb378[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Tue Dec 6 14:52:29 2022 +0900

    [조혜린] 파티방 참가 & Junit Test 완료 (#4)
    
    * 파티방 참가 완료
    
    * 파티방참가 junit test 완료

[33mcommit c05621f72d7507e9bfaf2c509308bbe9c7e13def[m
Merge: bb82997 a264b6e
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Tue Dec 6 14:52:08 2022 +0900

    Merge branch 'temp' into party-topic

[33mcommit bb82997b7b468edf82a3c856708b711248a50982[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Tue Dec 6 14:47:44 2022 +0900

    파티방참가 junit test 완료

[33mcommit ed776ee277bb49c49828666ba7e6983009375047[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Tue Dec 6 14:31:30 2022 +0900

    파티방 참가 완료

[33mcommit a264b6e6124706a3d43e4bd83bf47f50f2934b9a[m
Author: Hyerinism:) <112357320+Jo-HyeRin@users.noreply.github.com>
Date:   Tue Dec 6 14:26:23 2022 +0900

    [조혜린] 파티방 생성 & Junit Test 완료 (#3)
    
    * 파티방생성 완료
    
    * 파티방 생성 junit test까지 완료

[33mcommit 9b96bd970c578a7403373277cdd86ba43ea7921e[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Tue Dec 6 14:24:50 2022 +0900

    파티방 생성 junit test까지 완료

[33mcommit 8852bd09876235be6a6c07cc3fa47b61fe04e3b6[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Tue Dec 6 12:03:18 2022 +0900

    파티방생성 완료

[33mcommit e8d6c982fbf3427255571e37d231ef850fc0654f[m
Author: suhyeon <112357365+suucode@users.noreply.github.com>
Date:   Tue Dec 6 10:38:11 2022 +0900

    [임수현] Join/Login 완료 (#1)
    
    * join 완료
    
    * join시 토큰생성이 안됨
    
    * login 실행시 토큰생성오류
    
    * login 완료

[33mcommit a1e40d7819d1e9d9afdc6c815157ac2384cf0d3e[m[33m ([m[1;31morigin/setting-topic[m[33m)[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Thu Dec 1 18:09:39 2022 +0900

    초기 세팅 완료

[33mcommit 92ad9eff5fa2beb1123483d86c948cb60262b8d9[m[33m ([m[1;31morigin/master[m[33m)[m
Author: Jo-HyeRin <sayhyelin12@naver.com>
Date:   Thu Dec 1 18:07:51 2022 +0900

    initial commit
