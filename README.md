# 5팀사이렌오더 - MSA Capston Project


   |팀|성명|직급|사번|소속||
   |:----:|:------:|:------:|:------|:------|------|
   |5| 조영욱|대리|202000536|서비스혁신센터|서비스운영1팀|
   ||  이예찬|대리|202201385|서비스혁신센터|SharedService3팀|
   ||  한정재|대리|201401249|서비스혁신센터|SharedService2팀|  

- 분석설계 - 조영욱, 이예찬, 한정재
- SAGA Pattern - 이예찬, 한정재
- CQRS Pattern - 이예찬, 한정재
- Correlation / Compensation(Unique Key) - 조영욱, 이예찬, 한정재
- Request / Response (Feign Client / Sync.Async) - 이예찬, 한정재
- Gateway - 한정재
- Deploy / Pipeline - 조영욱
- Circuit Breaker -
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
- 음료 주문 완료후 고객 수령

특징
- 주문, 결제, 픽업 서비스는 분리된 서비스
- 하나의 시스템이 과중되더라도 나머지 시스템은 기능수행 가능
- 결제처리를 확인 한 후에 주문 요청 처리가 되도록 한다. 
- 주문상태 확인은 주문, 결제, 픽업 서비스 작동 유무와 무관하게 항상 확인할 수 있어야 한다. 



기능 검증 - 주문 요청
![image](https://user-images.githubusercontent.com/109929524/180945948-149faad7-c8b3-4969-8872-b9998c062359.png)

- 고객이 주문한다.
- 고객이 결제한다.
- 결제가 승인되면 상점으로 주문 요청된다.
- 음료준비가 완료되면 픽업 상태값이 변경된다.
- 주문 상태가 변경된다.

기능 검증 - 주문 취소

![image](https://user-images.githubusercontent.com/109929524/180946179-d3f1716c-ef46-4ea2-a96d-8622bc72d890.png)

- 고객이 주문 취소한다.
- 주문 정보를 삭제한다.
- Payment의 상태값이 변경된다.
- Pickup 상태값이 변경된다.
- 주문 상태가 변경된다.

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
```
- cd Orderdetail
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


![image](https://user-images.githubusercontent.com/17975717/180717689-7df38998-498a-4f49-84df-fe8a233073ee.png)

- ordered 이벤트 발생 시
![image](https://user-images.githubusercontent.com/109929524/180949505-544e037d-1edf-4fbb-829b-87eeba387a89.png)

- PaymentApproved 이벤트 발생 시
![image](https://user-images.githubusercontent.com/109929524/180949701-081eb097-9a8c-4149-a674-52c70fff6d74.png)

- orderCanceld 이벤트 발생 시
![image](https://user-images.githubusercontent.com/109929524/180949845-92617009-91f2-40fc-b8cf-257984bd9996.png)

- paymentCanceld 이벤트 발생 시 
![image](https://user-images.githubusercontent.com/109929524/180949967-6490c703-41d7-4254-a135-04df9c7c9c49.png)

- pickupStarted 이벤트 발생 시 
![image](https://user-images.githubusercontent.com/109929524/180950066-e3aebb9b-044f-4254-a0c2-14659776de56.png)

- pickupCanceld 이벤트 발생 시
![image](https://user-images.githubusercontent.com/109929524/180950184-f4148010-f74e-42e9-98cd-b12f198c1e28.png)

#Correlation / Compensation(Unique Key)
-  Order 서비스에서 주문취소 이벤트를 발행하였을때 Payment 서비스에서 주문취소 이벤트를 수신하여 작업 후 결제정보를 삭제하면서 결제 취소 이벤트 발행

* 주문취소 구현 - OrderCanceled.java 
![image](https://user-images.githubusercontent.com/109929524/180952696-16c5578e-790e-443c-866e-33bd2109e2ed.png)

- 주문 취소 시 orderCanceled 이벤트 발생
![image](https://user-images.githubusercontent.com/109929524/180953025-a81a3684-20c9-4d8d-a78d-1e420f0d992c.png)

![image](https://user-images.githubusercontent.com/109929524/180953657-2f74ec4b-4f6e-45fa-b7d4-3f831ffcbf87.png)


# CI/CD

- 도커 컨테이너 이미지 확인

- 클러스터 이름 확인
 eksctl get clusters

- 클러스터에 접속하기 위한 설정 다운로드
 aws eks --region ca-central-1 update-kubeconfig --name [Cluster Name]

- 접속이 정상적으로 되었다면
 kubectl get nodes

- ECR docker 명령을 로그인 시키기 위한 설정
- password 확인
  aws --region ca-central-1 ecr get-login-password
  docker login --username AWS -p 위에서나온긴패스워드 [AWS유저아이디-숫자로만된].dkr.ecr.ca-central-1.amazonaws.com

```

- 도커 컨테이너, 이미지 확인
```
- 도커 컨테이너 확인
docker container ls
- 도커 이미지 확인
docker image ls

- 도커 이미지를 빌드하고, Push 한다

인증 토큰을 검색하고 레지스트리에 대해 Docker 클라이언트를 인증합니다.
AWS CLI 사용

1. 인증 토큰을 검색하고 레지스트리에 대해 Docker 클라이언트 인증
aws ecr get-login-password --region ap-southeast-2 | docker login --username AWS --password-stdin 610244661545.dkr.ecr.ap-southeast-2.amazonaws.com

2. 도커 이미지 빌드
docker build -t frontend .

3.이미지에 태그를 지정
docker tag frontend:latest 610244661545.dkr.ecr.ap-southeast-2.amazonaws.com/frontend:latest

4. AWS 리포지토리로 푸시합니다.
docker push 610244661545.dkr.ecr.ap-southeast-2.amazonaws.com/frontend:latest


deployment.yml 파일에는 도커 이미지 부분이 변경되어 있어야 한다.
kubectl apply -f kubernetes/deployment.yml
kubectl apply -f kubernetes/service.yml

* 클러스터 확인
![image](https://user-images.githubusercontent.com/109929524/180955767-e684f756-6dcf-419c-b961-443daa524816.png)

* 위 External IP 로 접근 
![image](https://user-images.githubusercontent.com/109929524/180956183-612e0872-976c-4fbc-805d-c405543e115d.png)

* 주문정보 확인
![image](https://user-images.githubusercontent.com/109929524/180956469-0c69bd74-5764-402a-a0bb-7dfd21777a79.png)

# Gateway

- 게이트웨이를 사용하여 모든 API 서버들의 엔드포인트 단일화
Gateway 설정 파일 수정

![image](https://user-images.githubusercontent.com/109929524/181134934-e81159a1-bccc-49bb-a231-8e308de417f0.png)

- 단일진입점인 8088 포트로 주문 생성
![image](https://user-images.githubusercontent.com/109929524/181164698-af106499-803d-4166-8181-b03a346f2b87.png)

- 기존 8083 포트에서 결제 정보 확인
![image](https://user-images.githubusercontent.com/109929524/181164845-cd94eb3a-57c2-48d2-88bf-88e551a9b4d4.png)

- 단일 진입점인 8088포트에서 결제 정보 확인
![image](https://user-images.githubusercontent.com/109929524/181164930-d45cd4da-2300-4179-888c-be60afe209b6.png)

# Autoscale(HPA)
- kubectl get svc를 통하여 서비스 확인

![image](https://user-images.githubusercontent.com/109929524/181165173-9e48b5c9-b8a3-488b-8c6f-89a5eb4f2959.png)

- kubectl get pod 를 통해 Order 서비스 및 siege 상태 Running 확인

![image](https://user-images.githubusercontent.com/109929524/181165267-e0688d29-db52-4c9d-a2ec-bd5f55bb040a.png)

- 생성된 siege Pod 안쪽에서 정상 작동 확인

![image](https://user-images.githubusercontent.com/109929524/181165578-1c8edb35-ba72-414e-8957-c7f64b9871ab.png)

- metric server 설치 확인(리소스 사용량 확인)

![image](https://user-images.githubusercontent.com/109929524/181166020-f141d0fc-bd8f-48e1-b6c4-e0c0b6a5faf7.png)

- Auto Scaler 설정 
 : CPU가 20% 넘으면 리플리카 최대 3개까지 확장
 
 ![image](https://user-images.githubusercontent.com/109929524/181166199-4370d274-b2a6-4260-9e69-bb329b2ce8d2.png)


# AWS 

- AWS EC2 Dashboard

![image](https://user-images.githubusercontent.com/109929524/181135454-3d775d28-58e9-4983-b363-efc9a13d0f46.png)
 
- AWS ECR

![image](https://user-images.githubusercontent.com/109929524/181135359-f77e7639-630d-4ae0-847a-eb9771237591.png)


# 실습

* Order 주문 입력

'''
![image](https://user-images.githubusercontent.com/17975717/181136611-24a1b024-8b32-4fad-a8e9-3a62f9b19515.png)
'''

* Order 생성 완료
![image](https://user-images.githubusercontent.com/17975717/181136663-f3d54f53-8965-4ab4-94fb-a00f82d34390.png)

* Order Detail 주문목록에 생성
![image](https://user-images.githubusercontent.com/17975717/181137123-3994d1f2-2da1-40e4-9ee3-9f3127fc2a8b.png)

* 결제화면에 주문 생성
![image](https://user-images.githubusercontent.com/17975717/181137155-8a7e7042-742a-423a-bf77-12181d64db8c.png)

* 결제 버튼 클릭시 결제완료 되며, 결제 시간 생성
![image](https://user-images.githubusercontent.com/17975717/181137198-734a30e7-3094-4f2a-8706-a411b057bd42.png)

* Order Detail 에 결제완료 상태 표시
![image](https://user-images.githubusercontent.com/17975717/181137228-22e5be6e-24cc-426d-b212-b227ec4b2021.png)

* Pick Up 목록에 생성 되며, Pickup시 픽업완료 버튼 클릭
![image](https://user-images.githubusercontent.com/17975717/181137273-1006bd5f-0c7d-4e82-89b0-0022502f8517.png)

* 픽업 준비완료로 변경
![image](https://user-images.githubusercontent.com/17975717/181137336-9f77dca4-7b7a-4f28-a224-c2e69aed59de.png)


