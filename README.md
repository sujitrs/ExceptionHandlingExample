# Getting Started

### HTTP Method and Use Case Mapping

+	For **Create** use HTTP Method `POST`


+ 	For **Update** use HTTP Method `PATCH`


+	For **Get Single Record** use HTTP Method `GET`


+	For **Get Multiple Records** use HTTP Method `GET`


### HTTP Response Code and REST Action Mapping (Success)

| **_**        | **_** | **SYNC Resp. Body** | **ASYNC  No Resp. Body** | **SYNC No Resp. Body  (File Upload)** |
|:------------:|:-----:|:-------------------:|:------------------------:|:-------------------------------------:|
| \_           | 200   | 201                 | 202                      | 204                                   |
| Create       | N     | Y                   | Y                        | Y                                     |
| Update       | N     | Y                   | Y                        | Y                                     |
| Get Single   | N     | Y                   | Y                        | N                                     |
| Get Multiple | N     | Y                   | Y                        | N                                     |

### HTTP Response Code and REST Action Mapping (Failure)

| **_**        | **NOT Found** | **BAD Request (VNDR.ERROR)** | **Conflict (Versioning Issue)** | **UnAuth** | **Forbidden** | **Method Not Allowed** | **Gone(Archival)** | **Server Side  Exception** |
|:------------:|:-------------:|:----------------------------:|:-------------------------------:|:----------:|:-------------:|:----------------------:|:------------------:|:--------------------------:|
| \_           | 404           | 400                          | 409                             | 401        | 403           | 405                    | 410                | 500                        |
| Create       | N             | Y                            | Duplicate Record                | Y          | Y             | Y                      | N                  | Y                          |
| Update       | Y             | Y                            | Version Conflict                | Y          | Y             | Y                      | Y                  | Y                          |
| Get Single   | Y             | Y                            | N                               | Y          | Y             | Y                      | Y                  | Y                          |
| Get Multiple | N             | Y                            | N                               | Y          | Y             | Y                      | Y                  | Y                          |


### Two Options using @ControllerAdvice

1.	Return Response Entity {HTTP Response Code with Response Body}. Can be used for internal Microservices implementation.
2.	Return ONLY HTTP Response Code : To be used for all exceptions except for BAD_REQUEST_400. Can be used for all Internet Centric Microservices Implementation.

### Option 1

```
@ControllerAdvice
@Slf4j
public class TeamMemberExceptionHandler {
	   
	  
	  /*
		 * Option 1 to handle each error separately if you wish to communicate the error message separately in response entity
		 * + Can communicate specific messages and provide details of what went wrong
		 * 
		 */
		
		
		  private ResponseEntity<String> error(HttpStatus status, Exception e) {
		  log.error("Exception : ", e); return ResponseEntity.status(status).
		  body("Server is busy, please try after some time");
		  
		  }
		  
		  
		  @ExceptionHandler({RuntimeException.class})
		  
		  public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
		  
		  return error(INTERNAL_SERVER_ERROR, e);
		  
		  }
		  
		  @ExceptionHandler({HttpMessageNotReadableException.class})
		  
		  public ResponseEntity<String>
		  handleRunTimeException(HttpMessageNotReadableException e) {
		  
		  return error(HttpStatus.BAD_REQUEST, e);
		  
		  }
		  
		  
		  
		  
		  @ExceptionHandler({TeamMemberNotFoundException.class})
		  
		  public ResponseEntity<String>
		  handleNotFoundException(TeamMemberNotFoundException e) {
		  
		  return error(NOT_FOUND, e);
		  
		  }
		  
		  
		  
		  @ExceptionHandler({DataIntegrityViolationException.class})
		  
		  public ResponseEntity<String>
		  handleNotFoundException(DataIntegrityViolationException e) { return
		  error(HttpStatus.CONFLICT, e);
		  
		  }
```

### Option 2

```
 /*
	 * Option 2 to handle more than one exception; it should be used if you DO NOT wish to communicate the error message separately in response entity; it uses only HTTP response status
	 * + Can club multiple exception
	 * + well suited when you do not want to expose internal traces/messages to outside world, specially when your APIs are exposed directly on Internet/External Party
	 * - You cannot have any text message as response
	 * 
	 */
	
	  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	  
	  @ExceptionHandler({CannotCreateTransactionException.class,
	  SQLException.class, NullPointerException.class,ConnectException.class})
	  public void handle(Exception ex) {
	  log.error("INTERNAL_SERVER_ERROR Exception =",ex);//Prints Stack Trace }
	  
	  @ResponseStatus(HttpStatus.CONFLICT)
	  
	  @ExceptionHandler({DataIntegrityViolationException.class}) public void
	  handle(DataIntegrityViolationException ex) {
	  log.debug("CONFLICT Business Exception =",ex); // Not an error. Since its a
	  business exception. It is not required to logged at error level. This log
	  prints Stack Trace }
	  
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  
	  @ExceptionHandler({TeamMemberNotFoundException.class}) public void
	  handle(TeamMemberNotFoundException e) {
	  log.debug("NOT_FOUND TeamMemberNotFoundException =",e); // Not an error.
	  Since its a business exception. It is not required to logged at error
	  level.This log prints Stack Trace }
	  
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  
	  @ExceptionHandler({HttpMessageNotReadableException.class}) public void
	  handle(HttpMessageNotReadableException e) {
	  log.error("BAD_REQUEST Exception =",e); }
```



### Important Points to NOTE

+	For non-business exceptions, one should log stack trace in log file at ```ERROR``` level
+	For business exceptions, one should not log stack trace in log file, but should log the business exception details at ```DEBUG``` level in log file
+	This project can be used as template for CRU for one entity

### What's next

+	Composite Bean : Bean object being part of another bean as attribute member
+	Pagination and Sorting
+	Which one is better - bean validation or JSON Schema Parsing ?
+	OAuth implementation





