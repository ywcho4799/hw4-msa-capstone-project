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
- 기능 구현에 집중하기 위해 모델 최소화
- 주문 상세보기를 통해 CQRS 구현

![20220516_224327](https://user-images.githubusercontent.com/25494054/168607817-35bc6c76-4763-4f82-8b68-cddafd15d713.png)

기능 검증 - 주문 요청
![20220516_224327_LI](https://user-images.githubusercontent.com/25494054/168608474-a89cd66c-bb35-47ff-acfa-a038dfb2aa45.jpg)
- 고객이 주문한다.
- 고객이 결제한다.
- 결제가 승인되면 상점으로 주문 요청된다.
- 배달 시작된다.
- 주문 상태가 변경된다.

기능 검증 - 주문 취소
![20220516_224327_LI (2)](https://user-images.githubusercontent.com/25494054/168608490-9e503939-9bd0-49cf-81b8-f5690bffe006.jpg)
- 고객이 주문취소한다.
- 결제 취소된다.
- 상점으로 주문 취소 요청된다.
- 배달 취소된다.
- 주문 상태가 변경된다.

특징
- 주문, 결제, 배달 서비스는 분리된 서비스로, 하나의 시스템이 과중되더라도 나머지 시스템은 기능할 수 있어야 한다. 
- 결제처리를 확인 한 후에 주문 요청 처리가 되도록 한다. 
- 주문상태 확인은 주문, 결제, 배달 서비스 작동 유무와 무관하게 항상 확인할 수 있어야 한다. 
