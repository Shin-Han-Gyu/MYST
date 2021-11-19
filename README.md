## 프로젝트명 : MYST (My Simple/Share/Separate Todo)

__https://k5a503.p.ssafy.io__

<br/>

# 📜목차

1. [프로젝트 개요](#프로젝트-개요)
2. [프로젝트 명세](#프로젝트-명세)
   - [팀원 및 역할](#팀원-및-역할)
   - [IDE 및 개발환경](#ide-및-개발환경)
   - [개발 환경 조성](#개발-환경-조성)
3. [서비스 제공 기능](#서비스-제공-기능)
4. [기술 상세 명세](#기술-상세-명세)
   - [Front End](#front-end)
   - [Back End](#back-end)

---

<br/>

# 🎯프로젝트 개요
 - __바쁘고 복잡한 현대인들에게 작성하기 간단하고 개인 또는 그룹 단위로 할일을 등록하고 체크할 수 있는 TODO LIST 개발__

---

<br/>

# 📋프로젝트 명세

## 👨‍👩‍👦‍👦팀원 및 역할

김호석(팀장)  - Back-End, Server  
김상재        - Front-End
신한규        - Back-End
이예은        - Back-End
이희정        - Back-End 

<br/>

## 🔨IDE 및 개발환경

### Server OS
  - Ubuntu 20.04 LTS

### IDE
  - __Visual Studio Code__
  - __IntelliJ pro__

<br/>

## 💻개발 환경 조성
  - Back-End Server
    - nginx
    - openjdk:11
    - mysql
    - node
    - Jenkins

### 개발 환경 조성 순서
__백엔드 서버__
  1. AWS Ubuntu 20.04 LTS (쾌적환 환경을 위해 2개 운용 권장)
  2. docker 설치 및 위의 이미지 설치
  3. docker network 설정 - 백엔드(java), 프론트엔드(Nginx), DB(Mysql)
  4. mysql 설치 및 유저,DB 생성
  5. certbot을 통한 https 설정 -> etc/letsencrypt
  6. Jenkins 설치 및 바인딩
  7. Jenkins와 Gitlab연동
  8. Jenkins build에 따른 자동배포  

---

<br/>

# 🎈서비스 제공 기능

## 페이지별 기능 명세

### 웰컴 페이지
- 해당 웹앱의 기능을 간단한 문장으로 표현

### 메인 페이지
- 해당 유저이 해야하는 개인 또는 그룹 할일들을 표시
- 할일 등록 가능 (개인, 그룹)
- 할일 완료 체크 가능
- 팀별, 개인별 할일에 따라 구분하기 쉽도록 색을 부여함  

### 마이 팀 페이지
- 본인이 속한 팀 목록 열람 가능
- 팀 생성 가능
- 팀 이름, 팀 설명 확인 가능
- 팀별로 구분에 용이하도록 색 지정 제공

### 팀 디테일 페이지
- 팀원 목록 열람 가능
- 팀 가입 신청 확인 가능 (승인, 거절)
- 팀에서 등록한 할일들을 열람 가능

### 마이 페이지
- 회원정보 수정 가능 (미구현)
- 속한 팀 리스트 열람 가능

### 로그인, 회원가입 페이지
- 아이디, 비밀번호를 통한 로그인 가능
- 회원가입페이지에서 ID중복검사 후 이름, 비밀번호를 입력하여 회원가입

<br/>

## 사용자별 제공 기능

### 팀 리더
 - 팀 가입신청 승인 결정 권한

---

<br/>

# 🔍기술 상세 명세

## 💎Front End
Tool : __Vue.js 3__

### 네비게이터
  - 로그인 상태 확인
  - 팀페이지, 마이페이지, 로그아웃 가능

### 메인페이지
  - 할일 목록 불러오기 및 리스팅
  - 완료된 할일 표현 등

### 마이팀 페이지
  - 팀 고유색 지정을 위한 팔레트 기능 지원

### 팀 디테일 페이지
  - Modal을 통한 가입 승인, 거절 기능 구현

---

<br/>

## 🧩Back End
Tool : __Spring Frame Work__

### API 명세서
https://docs.google.com/spreadsheets/d/1JmI1P7KWEKpv4jFLYtbS5buzVdko5YW5MkJACP8Y-uE/edit#gid=0

<br/>

### DB Tables
 - PersonalTask -> 개인 할일 및 그룹 할일(FK)
 - GroupTask    -> 그룹 할일
 - Team         -> 그룹
 - TeamColor    -> 그룹 고유 색상
 - GroupMember  -> 그룹 멤버
 - GroupJoin    -> 그룹 가입신청
 - User         -> 유저

<br/>

### JPA
__JPA Repository, Query 통해 다양한 DB에 쉽게 적용 가능__
  - DB 테이블 구성을 JPA를 통해 구성  

<br/>

### JWT (JSON Web Token)
__Token을 활용하여 Session방식이 아닌 Token을 발급하여 로그인 이후 인증 매체로 활용__  

<br/>

### 구성
 - __controller__
   - Group   : __팀 및 멤버 처리__
     - 팀 생성, 색상 지정
     - 팀 생성, 조회
     - 멤버 조회 및 가입 승인, 거절 
   - Task : __할일 처리__
     - 할일 리스트 조회 (개인, 그룹)
     - 할일 생성, 조회, 삭제
   - User : __사용자 처리__
     - 사용자 가입, 로그인, 로그아웃
     - 아이디 중복검사

<br/>

 - __util__
   - JwtAuthenticationFilter - JWT Token Filter
   - JwtTokenProvider - Token 생성, 조회 유효검사

<br/>

 - __config__
   - SwaggerConfig - Swagger UI 사용을 위한 설정
   - WebMvcConfig
   - SecurityConfig - JWT Token Filter 외 다수 설정  

<br/>
