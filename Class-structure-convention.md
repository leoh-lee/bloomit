📦 클래스 선언 순서 컨벤션 (Class Member Ordering Convention)

이 문서는 Bloomit 프로젝트 및 협업 시에 클래스 내부 구성의 일관성과 가독성을 위해 사용하는 클래스 선언 순서 컨벤션입니다. 객체지향의 원칙과 실무적인 유지보수성을 고려한 구조입니다.

✅ 클래스 멤버 선언 순서

1. 상수 (static final)
2. static 변수
3. 인스턴스 변수
4. 생성자
5. public 메서드
   1. 조회 메서드 (get~, is~, read~)
   2. 비즈니스 로직 메서드 (update~, act~, handle~)
   3. void 메서드 (출력/알림 등 상태 변경 없는 부수 효과)
6. protected 메서드
7. private 메서드 `← 항상 클래스 맨 아래 배치`

🧭 각 항목별 목적 및 예시

1. 상수

`private static final int MAX_CHECKS_PER_DAY = 1;`

클래스의 성격, 규칙을 나타내므로 맨 위에 위치

2. static 변수

`private static final Logger log = LoggerFactory.getLogger(Habit.class);`

3. 인스턴스 변수
```java
private Long id;
private String title;
private List<HabitCheck> checks;
```

4. 생성자
```java
public Habit(String title) {
    this.title = title;
    this.checks = new ArrayList<>();
}
```
5. public 메서드
```java
public boolean isCheckedToday(LocalDate date) { ... }
public void check(LocalDate date) { ... }
public void printSummary() { ... }
```
읽기(get), 행위(update, handle), 부수 효과(print 등) 순서로 배치

6. protected 메서드
```java
protected void validateDomainRule() { ... }
```
상속 확장을 염두에 둔 동작

7. private 메서드 (가장 아래에 위치)
```java
private boolean alreadyChecked(LocalDate date) { ... }
private void sendNotification() { ... }
```
클래스의 내부 구현 상세를 드러내므로 가장 아래로 모아 정리
`
✅ 클래스는 문서처럼 읽히도록 구성되어야 함`

“이 클래스는 어떤 상태를 가지고, 어떤 방식으로 생성되며, 어떤 행동을 외부에 제공하는가?” → 위에서부터 자연스럽게 읽히도록 설계


이 컨벤션은 Bloomit 프로젝트 기본 기준이며, 도메인 특성에 따라 예외는 허용하되 클래스 구조는 항상 의도적으로 설계되어야 합니다.
