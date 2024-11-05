### 프로젝트 컨벤션 정리

> 개발을 진행하며 컨벤션화가 필요하다고 생각되는 경우 추가한다.

- 변수, 메서드명 네이밍
  - camelCase
  - 약자를 사용하지 않고 직관적으로 표현
- 엔티티 생성
  - 엔티티 기본 생성은 정적 팩터리 메서드 사용 (create())
  - 엔티티의 필드가 4개 이상인 경우 빌더 패턴 사용
- DTO와 엔티티 매핑
  - DTO에 정적 팩터리 메서드 사용 (fromEntity())
- 엔티티, DTO 사용 계층
  - Controller (requestDto) => Service (requestDto -> entity) => Repository (entity)
  - Repository (entity) => Service (entity -> responseDto) => Controller (responseDto)
- 응답 양식
  - 결과가 있는 경우: ResponseEntity<ApiResponse<결과>> 형식으로 반환
  - 결과가 없는 경우: ResponseEntity<ApiResponse<Void>> 형식으로 반환
- null 체크
  - ObjectUtils.isEmpty() 사용
  - 문자열의 경우 StringUtils.hasText() 사용
- equals(), hashcode() 생성
  - IDE 자동 생성
