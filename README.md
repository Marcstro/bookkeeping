Project description:

A basic API for creating accounts, making deposits or withdrawals, checking balance and lastly seeing deposit history for an account. 

Project Structure:

A SpringBoot application using jdk 17, built with maven and a H2 temporary database

Run instructions:

1. git clone
2. Build the maven project with command "mvn clean install"
3. run with command "mvn spring-boot:run"
4. Application is hosted on default springboot port 8080, i.e. http://localhost:8080/


API Endpoints
1. Get Balance

    Endpoint: GET /api/wallet/balance/{accountId}

    Description: Retrieves the current balance of the specified account.
   
    Returns: 200 OK with the balance as Double.

3. Transfer Funds

    Endpoint: POST /api/wallet/transfer
   
    Description: Transfers funds to an account (positive for deposit, negative for withdrawal).
   
    Params: accountId (String), amount (double)
   
    Returns: 200 OK with a message indicating the result (Transfer successful or other status).

5. List Transactions

    Endpoint: GET /api/wallet/transactions/{accountId}
   
    Description: Fetches the transaction history of the specified account.
   
    Returns: 200 OK with a list of transactions (List<Transaction>).

7. Create Account

    Endpoint: POST /api/wallet/create-account
   
    Description: Creates a new account with the provided account ID.
   
    Params: accountId (String)
   
    Returns: 200 OK with a message indicating the result (Account created successfully or other status).


Design choices:

My overall philosophy in this project was to create small examples of my skill and experience rather than making a complete and perfect project.
With a 2 hour limit I did not have time to, for example, create enough tests to get 100% test coverage.
Hence why some features like exception-handling occurs only once, or why only like 5% of the code is covered.

I used SpringBoot since that's the framework I have daily experience using.
I went with maven since I daily use Gradle and wanted a bit of a difference. No other choices were considered due to time limitation
When it came to domain classes I considered saving data in just Lists of accounts and transactions in the service layer or some saving layer. But decided to use a H2 "database" to show I could implement database features.

"Missed" feature designs:
Since this was a short project with a 2 hour time limit, here are some things I would've implemented had I had more time, without extending basic user features of the application.
- Component tests
- logging
- Sooooo much exception handling. Like handling erronous parameters
- Input validation of parameters
- Using DTOs instead of presenting POJOs
- prettified transaction JSON objects
- Maybe some basic authentication on endpoints
- returning specific http status

and more.



