**neueda-assignments

1. Url Shortner 
2. ATM Machine**

Steps to run the project.

1. Download or close the Project 
2. Open it in STS or IntelliJ
3. Update the pom.xml with all the dependencies 
4. Run as a Spring boot App
5. Testing can be done in Postman 



Note: Same original Link will generate unique values (short link) everytime we call the generate api and this is done using the  goggle hashing plugin murmur_32 and current time stamp. The lifetime of this url is also set to 150 seconds by default. I have also generated a docker image for the same. 

**1. Url Shortner API and values to test in postman **


1.1 Creating a shortLink 

```
Post url : http://localhost:8080/api/generate

```

```
body : 
{
  "url":"https://www.amazon.co.uk/all-new-blink-outdoor-wireless-weather-resistant-hd-security-camera-with-2-year-battery-life-motion-detection-1-camera-system/dp/B088CZW8XC/ref=gbps_img_m-2_b6bc_fd384b6b?smid=A3P5ROKL5A1OLE&pf_rd_p=c7416edb-2cce-4fca-8ec2-3a43dafab6bc&pf_rd_s=merchandised-search-2&pf_rd_t=101&pf_rd_i=341686031&pf_rd_m=A3P5ROKL5A1OLE&pf_rd_r=QKEZDE26ZZ3Y0WT9NT1Z"

}
```

Response :
```
{
 "originalUrl": "https://www.amazon.co.uk/all-new-blink-outdoor-wireless-weather-resistant-hd-security-camera-with-2-year-battery-life-motion-detection-1-camera-system/dp/B088CZW8XC/ref=gbps_img_m-2_b6bc_fd384b6b?smid=A3P5ROKL5A1OLE&pf_rd_p=c7416edb-2cce-4fca-8ec2-3a43dafab6bc&pf_rd_s=merchandised-search-2&pf_rd_t=101&pf_rd_i=341686031&pf_rd_m=A3P5ROKL5A1OLE&pf_rd_r=QKEZDE26ZZ3Y0WT9NT1Z",
    "shortLink": "localhost:8080/api/e32b0990",
    "expirationDate": "2021-07-12T22:43:35.1768411"
}
```
1.2 : Redirect to the original link

```
Get url : localhost:8080/api/{unique-values}

```

Response :
```
It is redirect to the Amazon product page as per the given example.
```




**2. ATM Machine **

2.1 Retriving balance from the DB if the credentials are correct 

Url:
```
GET url : http://localhost:8080/api/accountdetails

```

```
body : 
{
    "accountNumber":123456789,
    "pin":1234

}
```
Response :
```
{
    "accountNumber": 1234,
    "balance": 2220.0
}
```
2.2 Enter an amount to be withdrawn

First it validates if the user is valid and then retrives the money if the request amount is less then balance else it throws and exception.

Url:
```
Post url : http://localhost:8080/api/withdraw

```

```
body : 
{
    "accountNumber":123456789,
    "pin":1234
    "amount": 150
}
```

Response :
```
On Failure 
{
    "errorMessage": "Invalid Account Number or Pin. Please check and Retry",
    "requestedURI": "/api/withdraw"
}

On success 
{
    "accountNumber":123456789,
    "balance": 650
}
```





