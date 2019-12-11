# Getting Started

### HTTP Method and Use Case Mapping

+	For **Create** use HTTP Method `POST`


+ 	For **Update** use HTTP Method `PATCH`


+	For **Get Single Record** use HTTP Method `GET`


+	For **Get Multiple Records** use HTTP Method `GET`


| **_**        | **_** | **SYNC Resp. Body** | **ASYNC  No Resp. Body** | **SYNC No Resp. Body  (File Upload)** |
|:------------:|:-----:|:-------------------:|:------------------------:|:-------------------------------------:|
| \_           | 200   | 201                 | 202                      | 204                                   |
| Create       | N     | Y                   | Y                        | Y                                     |
| Update       | N     | Y                   | Y                        | Y                                     |
| Get Single   | N     | Y                   | Y                        | N                                     |
| Get Multiple | N     | Y                   | Y                        | N                                     |
