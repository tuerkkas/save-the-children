# save-the-children
Store user information

#Deployment steps:

1. Run compose.yaml file

    docker-compose.exe -f %file_path%\compose.yaml -p save-the-children-users up -d

   Run the application in your IDE.

2. Securization is using Basic Authentication which generates a Token for acceding to the API.

2.1   Obtain Token:
   http://localhost:8080/api/auth/token
      Use BasicAuthentication, then a token will be provided for accessing to the API. It will remain active from 1 hour.
      I used JWA with symmetric encryption. I would like to make login using AOUTH2 login agains GitHub but I have no time so I had to take this decision for get the deadline date.

         userName: save-the-children
         password: £$ASCTGDSFESAFDG

      I have not time to encrypt the credentials. Sorry for that

3. Premises
4. 
   1. In order to create a donation the user needs to exist on database
      POST  http://localhost:8080/api/users
            {"firstName":"Jorge","lastName":"diaz", "email":"jorgesave-the-children.com"}
   2. Send donation
      POST http://localhost:8080/api/donations
      {"userId":"8f2f74c8-1f69-45a8-abc3-b84ab22fc299","donation":"500","currency":"USD"}
   
   3. Get user information and list of donations
      GET http://localhost:8080/api/users/8f2f74c8-1f69-45a8-abc3-b84ab22fc299

   4. Considerations
      All data will be encrypted on the Database.
      I could not make all Unit test required, i created all I have time.
      I would like to implement Data sanitization (I validated the data with a minimum requirements) and enable Cross-Origin Resource Sharing (CORS) but I have not time for.
   