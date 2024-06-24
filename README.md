### 요구사항

- [x] 사용자는 개인 정보를(이메일,비밀번호,이메일 수신동의) 통해 회원 가입을 한다.
- [x] 사용자가 이메일과 비밀번호를 통해 로그인을 한다.
- [ ] 사용자는 강의 수강 신청을 할 수 있다.
    - [ ] 강의 신청중 사용자의 등급에 따라, 결제 금액을 할인 받을 수 있다.
- [ ] 사용자는 자기가 수강한 강의 목록을 볼 수 있다.
- [ ] 강사 신청을 할 수 있다.
    - [ ] 운영자가 강사 여부를 심사한다.
- [ ] 강사는 강의를 관리할(등록,수정,삭제)수 있다.
- [ ] 강사는 강의에 필요한 자료를 관리할(등록,수정,삭제) 수 있다.
- [ ] 강사는 수강자들에게 알림을 이메일로 발송할 수 있다.
- [ ] 강사는 자신의 특정 강의를 수강하는 강의자들을 조회할 수 있다.

---

### Entity
#### 사용자
```
MEMBER
id VARCHAR(50) NOT NULL PRIMARY
email VARCHAR(50) NOT NULL
password VARCHAR(50) NOT NULL
grade ENUM
```
#### 강의 결제
```
LECTURE_PAYMENT
id BIGINT AUTO_INCREMENT PRIMARY
payment_key VARCHAR(50) NOT NULL
payment_method VARCHAR(50) NOT NULL
lecture_id BIGINT NOT NULL REFERENCE LECTURE
member_id VARCHAR(50) NOT NULL REFERENCE MEMBER
```
#### 강의
```
LECTURE
id VARCHAR NOT NULL PRIMARY
title VARCHAR(50) NOT NULL
description TEXT NOT NULL
price BIGDECIMAL NOT NULL
teacher_id VARCHAR NOT NULL REFERENCE TEACHER
```

#### 수강생 테이블
```
MEMBER_LECTURE
member_lecture_id BIGINT AUTO_INCREMENT NOT NULL
member_id VARCHAR NOT NULL REFERENCE MEMBER
lecture_id VARCHAR NOT NULL REFERENCE LECTURE
```
#### 목차
```
STEP
id BIGINT AUTO_INCREMENT PRIMARY
content TEXT NOT NULL
lecture_id BIGINT NOT NULL REFERENCE LECTURE
```
#### 자료
```
CONTENT
content_id VARCHAR NOT NULL PRIMARY
file_url VARCHAR NOT NULL PRIMARY
teacher_id VARCHAR NOT NULL REFERENCE TEACHER
```
#### 강사
```
TEACHER
teacher_id VARCHAR NOT NULL PRIMARY
email VARCHAR(50) NOT NULL
password VARCHAR(50) NOT NULL
```
#### 강의 알림
```
LECTURE_NOTIFICATION
content TEXT NOT NULL
lecture_id VARCHAR NOT NULL REFERENCE LECTURE
teacher_id VARCHAR NOT NULL REFERENCE TEACHER
```
#### 강사_신청_자료

```
TEACHER_REQUEST_FORM
member_name VARCHAR(50) NOT NULL
reason TEXT NOT NULL
email VARCHAR(50) NOT NULL
phone_number VARCHAR(50) NOT NULL
link VARCHAR(50) NOT NULL
```
