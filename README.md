
# Notice-Board

---
# 목적
- 대용량 트래픽을 고려한 애플리케이션 개발 (초당 1000tps 이상의 게시글 검색 API)
- 객체지향과 디자인 패턴을 적용 및 가독성을 고려한 코드 작성 방법
- 각 단계마다 테스트 코드 작성
- 모니터링 및 트러블 슈팅
- 젠킨스 툴로 배포 자동화

---
# 사용 기술
- Java 17, WebFlux, Mongodb, Docker

---
# 프로그램 주요 기능
- 회원
    - 가입, 탈퇴
    - 아이디 및 닉네임 중복체크
    - 비밀번호 암호화
    - 로그인, 로그아웃
- 게시판
    - 카테고리 관리
        - 추가, 삭제, 수정
    - 게시글 관리
        - 게시글 & 파일 추가, 삭제, 수정, 조회
        - 유저 정보, 게시글 제목, 게시글 내용 등
    - 게시글 검색 기능
        - 작성 유정 아이디
        - 게시글 제목, 게시글 내용 등을 통해 검색
        - 태크 작성 및 조회 기능
    - 댓글 작성 기능
- 어드민
    - 공지글 추가 기능

---
# 아키텍처

(추가예정)

---
Day 1 (10/24)

멀티 모듈 프로젝트 생성
유저 관련 기본 API 작성