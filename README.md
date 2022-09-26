# NewsApp
Application for get news applying mvvm architecture.

## Architecture

The architecture used in this project is modular MVVM.
The app makes use of the FirebaseAuth service and also the news API https://newsapi.org/

The application is composed of

* Module app
* Module core
* Module presentation
* Module login
* Module home


### Module core
 In this module the logic of the application is handled, such as BaseFragment, views, repositories for services and resources of strings and colors.

### Module presentation
 Custom components such as buttons and text boxes are built in this module.

### Module logi
 The login module manages the navigation and design of the login and user registration, here it connects to Firebase Auth to create and make requests.

### Module home
 The home module manages the navigation of the news flow, for the services coroutines and retrofits were used here.

## Functionality

The user creates an account and will then be redirected to login to log in with their credentials.

Then a GET request is made to the news API with a maximum of 5 news per request.

When detecting that the user has reached the end of the news, it will automatically request another 5 more.

Each news can be seen in more detail just by pressing the next button (->) and there is also a link to the website of the source that published the news.

### Login
![image](https://user-images.githubusercontent.com/35543308/192353179-088530d1-6014-40bb-b856-07dcbd20f5e9.png)

### Signup
![image](https://user-images.githubusercontent.com/35543308/192353281-090ec031-9210-440a-8425-3ac7f5840109.png)

### Home
![image](https://user-images.githubusercontent.com/35543308/192353330-195bfdcf-981a-4c5c-a7f3-857c4a4ae574.png)

### Detail
![image](https://user-images.githubusercontent.com/35543308/192353402-be707e9b-a231-4e1b-8db4-2749d86d5c7a.png)

### Source web
![image](https://user-images.githubusercontent.com/35543308/192353451-88b88d27-b293-40aa-a4b9-fa9ff7546ee3.png)



