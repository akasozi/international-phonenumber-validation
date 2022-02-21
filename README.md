# international-phonenumber-validation

1. Build a docker image for the backend using the command

   docker build --tag=international-phone-validator:latest .
   
2. Once done use the command

   docker run -p8081:8081 international-phone-validator:latest
   

3. SAMPLE GET REQUEST

    http://localhost:8081/api/v1/phone-numbers?pageNo=0&pageSize=50&sortBy=id&country=Ethiopia&state=VALID
    
    http://localhost:8081/api/v1/phone-numbers?pageNo=0&pageSize=50&sortBy=id&country=Ethiopia&state=NOT_VALID
    
4. SAMPLE RESPONSE 

         [
             {
                 "phoneNumber": "(251) 9773199405",
                 "status": "NOT_VALID"
             },
             {
                 "phoneNumber": "(251) 9119454961",
                 "status": "NOT_VALID"
             }
         ]   
   
   
