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
