# 5팀사이렌오더 - MSA Capston Project


   |팀|성명|직급|사번|소속||
   |:----:|:------:|:------:|:------|:------|------|
   |5| 조영욱|대리|202000536|서비스혁신센터|서비스운영1팀|
   ||  이예찬|대리|202201385|서비스혁신센터|SharedService3팀|
   ||  한정재|대리|201401249|서비스혁신센터|SharedService2팀|  

- 분석설계 
- SAGA Pattern - 
- CQRS Pattern - 
- Correlation / Compensation(Unique Key) - 
- Request / Response (Feign Client / Sync.Async) -
- Circuit Breaker - 
- Gateway - 
- Deploy / Pipeline - 
- Autoscale(HPA) - 
- Self-Healing(Liveness Probe) - 
- Zero-Downtime Deploy(Readiness Probe) - 
- Config Map / Persistence Volume -d 
- Polyglot - 


# 분석설계
- 주문 상세보기를 통해 CQRS 구현

- 고객이 주문한다
- 고객이 결제한다 
- 결제가 승인되면 상점으로 주문 요청 된다
- 음료 주문 완료후 고객 수령

특징
- 주문, 결제, 픽업 서비스는 분리된 서비스
- 하나의 시스템이 과중되더라도 나머지 시스템은 기능수행 가능
- 결제처리를 확인 한 후에 주문 요청 처리가 되도록 한다. 
- 주문상태 확인은 주문, 결제, 픽업 서비스 작동 유무와 무관하게 항상 확인할 수 있어야 한다. 



기능 검증 - 주문 요청
![image](https://user-images.githubusercontent.com/109929524/180700105-272e02ea-3079-44f7-af04-e6abec18388f.png)

- 고객이 주문한다.
- 고객이 결제한다.
- 결제가 승인되면 상점으로 주문 요청된다.
- 배달 시작된다.
- 주문 상태가 변경된다.

기능 검증 - 주문 취소

![image](https://user-images.githubusercontent.com/109929524/180700149-e356f7d1-94c6-4216-b1c4-902572832880.png)

-고객이 주문 취소한다.
-

# 실행

```
- cd gateway
- mvn spring-boot:run
```
```
- cd order
- mvn spring-boot:run
```
```
- cd payment
- mvn spring-boot:run
```
```
- cd Store
- mvn spring-boot:run
```
# CQRS

- 주문 발생(취소), 결제(취소), 픽업(취소) 이벤트 발생 시 주문, 결제 상태값 주문금액(금액확인)을 고객이 조회할 수 있도록 CQRS로 구현하였습니다.

```
비동기식으로 처리되어 이벤트 기반의 Kafka를 통해 처리되어 별도 Table에 관리한다.
order, payment, store Aggregate의 '마이페이지' 형식과 같이 통합 조회가 가능하다.
Modeling
```
![image](https://user-images.githubusercontent.com/17975717/180715568-3d39261a-df66-42f4-8871-800be3daf063.png)
