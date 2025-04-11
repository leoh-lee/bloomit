<img src="https://github.com/user-attachments/assets/3c519e4c-9b7a-46de-8367-15b67d2626f1" alt="Bloomit CI" title="Bloomit CI" width="300">

# 🌸 Bloomit

**Bloomit**는 감성 기반의 습관 형성 및 책 리뷰 관리 플랫폼입니다.  
당신의 성장 습관에 꽃이 피듯, 매일의 실천이 쌓여 더 나은 나로 피어납니다.

---

## 🌱 프로젝트 개요

Bloomit은 **습관 형성**, **독서 관리**, **리뷰 기록**, **감정 기반 트래킹**을 통해  
자기 성장과 기록의 루틴을 함께 도와주는 **All-in-One 성장 플랫폼**입니다.

- 🌸 습관을 실천할 때마다 나무에 벚꽃잎이 피고, 실천하지 않으면 꽃잎이 떨어지는 **감성 피드백** 시스템
- 📚 책을 검색하고, 리뷰 및 독서 노트를 남기며 **자기 성찰과 기록**
- 🗂️ 개인 서재 기능으로 책을 분류, 보관
- 📝 중요한 문장/밑줄/메모 등 독서 중 인상 깊었던 내용을 기록
- 🌟 리뷰 랭킹, 이달의 독후감 등 커뮤니티 기능 제공 예정

---

## 🧰 기술 스택

| 영역 | 기술                                                             |
|------|----------------------------------------------------------------|
| Backend | Java 21, Spring Boot 3, JPA, Spring Security, Redis, WebClient |
| Database | MySQL                                                          |
| Infra | AWS EC2, S3, RDS, Docker                                       |
| Monitoring | Grafana, Prometheus                                            |
| CI/CD | GitHub Actions (예정)                                            |
| 기타 | Kafka (보상 트랜잭션용), TestContainers (테스트)                         |

---

## 🧩 주요 기능

### ✅ 습관 기능
- 습관 추가 및 수정
- 습관 실천 여부 체크
- 벚꽃 피드백 애니메이션
- 습관별 실천 히스토리 확인

### 📖 독서 기능
- 책 검색 (API 연동 예정)
- 책 리뷰 작성 및 수정
- 독서 노트, 밑줄, 인상 깊은 문장 기록
- 개인 서재 생성 및 책 분류

### 💬 커뮤니티 (MVP 이후)
- 리뷰 랭킹
- 이달의 독후감 선정
- 댓글 및 공감 기능

---

## 🛠️ 프로젝트 구조 (예시)

```bash
bloomit/
├── bloomit-api/               # Spring Boot Backend
│   ├── domain/                # 도메인 계층 (엔티티, 레포지토리 등)
│   ├── application/           # 서비스 계층
│   ├── interfaces/            # 컨트롤러, API 요청/응답
│   └── config/                # Security, Web 설정 등
├── bloomit-batch/             # 예약 배치 작업 (예: 미실천 습관 업데이트)
├── bloomit-common/            # 공통 유틸, 예외 처리, 응답 포맷 등
└── README.md
```

## 🧭 향후 계획
- OAuth 기반 소셜 로그인
- 모바일 앱 Flutter 버전 (혹은 React Native)
- AI 기반 추천 습관 및 독서 큐레이션
- Elasticsearch 도입으로 책/리뷰 고속 검색