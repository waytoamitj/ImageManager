Model Entity
User : User entitled for registration. Registered user can upload, view and delete images through Imgur exposed APIs.
Image : Artifact to upload, view and delete in Imgur application.

Service
User Service : To perform CRUD operations on registered User.
Imgur Service : Service layer to interact with Imgur exposed APIs and execute upload, view and delete image operations for registered User.

Repository
User repository: Extends JpaRepository to perform CRUD operations on the User entity. It includes a method to find a user by Id.

Controller
User Controller: User Controller defines REST endpoints to perform necessary actions. APIs exposed

Register User :  /api/users/register  (POST)
User Update : /api/users/{userId}  (PUT)
User Delete : /api/users/{userId}  (DELETE)
Users list : /api/users/list (GET)

Image Upload in Imgur app by authenticated and authorized User : /api/users/{userId}/images/upload   (POST)
Fetch uploaded image in Imgur app for authenticated and authorized User :  /api/users/{userId}/images/{imageId}   (GET)
Delete image in Imgur app by authenticated and authorized User :  /api/users/{userId}/images/{imageDeleteHash}    (DELETE)

![image](https://github.com/waytoamitj/ImageManager/assets/171623556/9145d352-fb57-4f50-b0db-a37cd6833806)


**Application Sucessful build and deploy with test case coverage:**

![image](https://github.com/waytoamitj/ImageManager/assets/171623556/717c0c53-68e8-41ce-88ab-a82ec1d336c4)

**Test Users registered and available in H2 database**

![image](https://github.com/waytoamitj/ImageManager/assets/171623556/619e168d-46cd-44c8-ae52-e09a0b965f8f)         

![image](https://github.com/waytoamitj/ImageManager/assets/171623556/8c37f9a6-8269-4e05-8b9a-65dc8831d504)

**New User Registration user id=11**

![image](https://github.com/waytoamitj/ImageManager/assets/171623556/dc06784f-fff3-452f-a6f9-822a8e24d1e5)           

![image](https://github.com/waytoamitj/ImageManager/assets/171623556/79bfe1b6-80ed-4fe4-afa9-1c50fa36faff)


**user id=11 updated to username = user99 and password= password99**

![image](https://github.com/waytoamitj/ImageManager/assets/171623556/72048435-ec49-4949-83bd-841fdeb12b64)

![image](https://github.com/waytoamitj/ImageManager/assets/171623556/ccb432d7-5c3a-40d4-98fd-fd48739f1de5)

**Deletion of user id= 11**

![image](https://github.com/waytoamitj/ImageManager/assets/171623556/9709dd76-d4b0-4ea8-8923-11a9b74313a1)

![image](https://github.com/waytoamitj/ImageManager/assets/171623556/3f83b9d2-19da-45fc-80db-6743e5d3094d)

**Image upload by user id=10**

![image](https://github.com/waytoamitj/ImageManager/assets/171623556/3c0dc782-6a91-467c-b91b-675e64b27677)

![image](https://github.com/waytoamitj/ImageManager/assets/171623556/92998fd5-ac5f-4d17-85e0-306acd5aea69)

![image](https://github.com/waytoamitj/ImageManager/assets/171623556/a1cf331e-86f1-4cf6-a623-722870037623)

**View Image upload by user id=10 is associated with user id=10**

![image](https://github.com/waytoamitj/ImageManager/assets/171623556/fdd06738-b0ec-4c90-bf21-ef19430cf0c5)

**View,Delete operations on Image is only allowed by registered user who uploaded this image. Any other User Id except 10 will not be able to view, delete this image id=**1dYqeal**.**

**2 Images uploaded by user id=10  i.e Image id=** **1dYqeal**  and  **6k5lc43**
 
![image](https://github.com/waytoamitj/ImageManager/assets/171623556/d4936dc2-be15-4ade-8e8d-a1986ebb8e1c)

**Userid =9  trying to delete image id= 1dYqeal** **but not allowed to delete**

![image](https://github.com/waytoamitj/ImageManager/assets/171623556/856b8297-f9cc-4623-b53a-fd978cafbaf9)

**User id = 10 deleting the same image id = 1dYqeal**

![image](https://github.com/waytoamitj/ImageManager/assets/171623556/14c45236-129c-4c7c-8811-8786ec09b931)

**Associated image also deleted from user id=10**

![image](https://github.com/waytoamitj/ImageManager/assets/171623556/39ae5865-3269-45c9-a890-c84921293a3b)






















