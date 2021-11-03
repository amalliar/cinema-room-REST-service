# API specification
```GET /seats```  
Get the list of available seats.  

```POST /purchase {"row": 3, "column": 4}```  
Purchase a ticket for the specified seat;  
On success returns a string token of the sold ticket.  

```POST /return {"token": "9b674be1-b42e-4659-b54f-0663e5d4b218"}```  
Return a ticket with provided token.  

```POST /stats?password=super_secret```  
Display statistics on sold tickets.  
