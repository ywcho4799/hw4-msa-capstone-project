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
- Config Map / Persistence Volume - 
- Polyglot - 


# 분석설계
- 주문 상세보기를 통해 CQRS 구현

- 고객이 주문한다
- 고객이 결제한다 
- 결제가 승인되면 상점으로 주문 요청 된다
- 주문 상태가 변경된다 
- 음료 주문 완료후 고객 수령

특징
- 주문, 결제, 픽업 서비스는 분리된 서비스
- 하나의 시스템이 과중되더라도 나머지 시스템은 기능수행 가능
- 결제처리를 확인 한 후에 주문 요청 처리가 되도록 한다. 
- 주문상태 확인은 주문, 결제, 픽업 서비스 작동 유무와 무관하게 항상 확인할 수 있어야 한다. 

# 실행
```
- cd order
- mvn spring-boot:run
```
- cd payment
- mvn spring-boot:run
- cd delivery
- mvn spring-boot:run
- cd orderDetail
- mvn spring-boot:run
