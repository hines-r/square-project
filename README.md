# Square Mobile Project

## Build tools & versions used

- Gradle 7.3.3

## Steps to run the app

1. git clone
2. Open project in Android Studio (I used version 2021.2.1 Patch 1)
3. Sync Gradle and build project
4. Run project on Android emulator SDK versions 21-32

## What areas of the app did you focus on?

The main area I focused on for this app was the overall architecture and structure of it. I really enjoy learning about design patterns and opted to try implementing MVVM (Model-View-ViewModel) to separate the logic of the app from the interface. This may have been more time consuming, but I believe it really improves the way the app is built.

## What was the reason for your focus? What problems were you trying to solve?

I primarily focused on the architecture and structure by using MVVM because I believe design patterns are important. They're essentially like a common language developers can understand no matter the programming language or technology used. In this instance, utilizing MVVM architecture allows the app to be more maintainable, extensible, and easier to test. One may argue this may be overkill in a smaller application, but it will really shine when the application grows. I believe it makes understanding the application easier as well because of a proven and well defined architecture. Because of this, it is also easier to find solutions to problems you may experience online because of the commonality between applications.

## How long did you spend on this project?

I spent around ~8 hours developing this project.

## Did you make any trade-offs for this project? What would you have done differently with more time?

I tried to not make many trade-offs in my mind when coding this project. I tried to make it in such a way that it would be easy to add on to in the future. At least that was the hope. That said, I definitely came out knowing more than I do now. I'm sure I would have gone about it differently if I started again. I went back and forth between certain things such as using activities versus fragments, design pattern choices, and I also spent more time on unit tests that I would have liked.

If I had more time, I would have loved adding more features to the app. I was interested in making a detail view for when you tap on an employee but I know the instructions said not to add more screens. I also wanted to add a filter and way of sorting the employees however you like.

## What do you think is the weakest part of your project?

I feel like the weakest part of my project may be the unit tests. I was struggling to come up with a solution to test the live data of my view model. After much fiddling with it, I came up with a solution which I found on Stack Overflow. I'm still not sure if the tests are sufficient though. I simply tested for the state being set on the live data and if the list of employee objects was being populated.

Besides the tests, perhaps the UI could be spruced up and maybe more work could have been done on the swipe refresh mechanism I'm using to refresh the employee list. I would have liked it to have nicer user feedback when they pull to refresh. It currently displays a progress bar, but maybe it could have been better.

## Did you copy any code or dependencies? Please make sure to attribute them here!

- Moshi 1.13.0
- Retrofit 2.9.0
- Glide 4.13.0
- OkHttp MockWebServer 4.10.0

MVVM and Retrofit:
[https://www.youtube.com/c/PhilippLackner](https://www.youtube.com/c/PhilippLackner)

Unit testing:
[https://stackoverflow.com/questions/63339306/viewmodel-unit-testing-multiple-view-states-with-livedata-coroutines-and-mockk](https://stackoverflow.com/questions/63339306/viewmodel-unit-testing-multiple-view-states-with-livedata-coroutines-and-mockk)

## Is there any other information you'd like us to know?

This project was really fun for me. The first programming language I have ever learned was Java, but I always wanted to learn how to use Kotlin. Because of this, I took the time to begin learning how to use it. It's fairly easy to pick up knowing both Java and C#. I also come from a mobile app development background using Flutter. Learning more about native development with Kotlin was interesting and I can see some subtle similarities working between the two.

Thanks for looking at my project and reading!
