### Τεχνικές Προδιαγραφές

* Packaging: war
* Java Version: 17
* Jakarta EE Version: 10
* Server: tomcat-11.0.4
* Jersey (JAX-RS): RESTful Web Services
* maven-war-plugin για δημιουργία .war αρχείου

---
### Τα end points που δημιούργησα είναι τα ακόλουθα:
1. Ανάκτηση στοιχείων δικαιούχου.
   
    http://localhost:8080/api/beneficiaries/{beneficiaryId}

    παράδειγμα απάντησης του end point:

   {
   "beneficiaryId": 180,
   "firstName": "Jsandye",
   "lastName": "Tremayne"
   }

2. Ανάκτηση των λογαριασμών ενός δικαιούχου.

   http://localhost:8080/api/accounts/{beneficiaryId}

   παράδειγμα απάντησης του end point:

   [
   {
   "accountId": 2,
   "beneficiaryId": 183
   },
   {
   "accountId": 261,
   "beneficiaryId": 183
   }
   ]
3. Ανάκτηση των συναλλαγών ενός δικαιούχου.

   http://localhost:8080/api/transactions/{beneficiaryId}

   παράδειγμα απάντησης του end point:

   [
   {
   "transactionId": 4093,
   "accountId": 1040,
   "amount": 96.1,
   "type": "deposit",
   "date": "2023-08-07"
   },
   {
   "transactionId": 3536,
   "accountId": 1270,
   "amount": 207.6,
   "type": "deposit",
   "date": "2023-04-05"
   }
   ]

4. Ανάκτηση του υπολοίπου των λογαριασμών ενός ανθρώπου.

   http://localhost:8080/api/transactions/{beneficiaryId}/balance

   παράδειγμα απάντησης του end point:

   {
   "beneficiaryId": 183,
   "balances": [
   {
   "accountId": 2,
   "balance": 399.8
   },
   {
   "accountId": 261,
   "balance": 1613.3999
   }
   ]
   }

5. Ανάκτηση της μεγαλύτερης ανάληψης για έναν δικαιούχο τον τελευταίο μήνα.

   http://localhost:8080/api/transactions/{beneficiaryId}/maxWithdrawal

   παράδειγμα απάντησης του end point:

   {
   "beneficiaryId": 180,
   "withdrawal": 0.0
   }
   
