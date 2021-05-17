
說明
1. 建立工單時會塞到activemq內, 若有上線的使用者會自動分派
2. 上線的使用者會依照腳色塞到redis作暫存   
3. 接收工單的客服人員可以往上轉單給TL,若TL沒空的話會轉給PM
4. verify階段可以打包出docker-compose.yml 與 Dockerfile, 若執行環境有docker-compose可直接啟動 (包含redis)
5. swagger: http://localhost:8085/swagger-ui.html
   
API列表
* FrontController
  1. POST  **/v1/front/order**  <br> 建立工單
  1. GET  **/v1/front/order** <br>  顯示queue中工單數量 
    
* UserController
  1. GET  **/v1/user/search**  <br> 取得使用者列表
  1. GET  **/v1/user** <br>  取得使用者
  1. POST  **/v1/user/login**  <br> 模擬登入
  1. POST  **/v1/user/logout**  <br> 模擬登出
  1. POST  **/v1/user/complete**  <br> 完成工單
  1. POST  **/v1/user/trans**  <br> 轉發工單 (由於tl跟pm僅有一人) 發出轉單需求後會找是否有可以轉的人         