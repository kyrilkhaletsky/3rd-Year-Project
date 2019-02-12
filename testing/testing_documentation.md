# ShoppingPal Testing Documentation
![](code/ShoppingPal/app/src/main/res/mipmap-xxhdpi/ic_launcher.png)

## CA326

Ben Kelly & Kyrylo Khaletskyy

## Table of Contents
- [1. Interface Testing](#1-interface-testing)
- [2. Unit Testing](#2-unit-testing)
- [3. Android Studio Logs](#3-android-studio-logs)
- [4. User Testing](#4-user-testing)
- [5. Heuristic Analysis](#5-heuristic-analysis)
- [6. Shneiderman's 8 Golden Rules](#6-shneiderman's-8-golden-rules)


## 1 Interface Testing

Below are screenshots of the ShoppingPal home screen on 5 different device screens.

* Samsung J3   

<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/master/testing/media/Screenshot_2018-03-09-01-18-35.png" width="213.8" height="380" />

* OnePlus 5   

<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/master/testing/media/WhatsApp%20Image%202018-03-09%20at%201.07.25%20AM.jpeg" width="213.8" height="380" />

* Samsung Galaxy S6

<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/master/testing/media/WhatsApp%20Image%202018-03-09%20at%201.12.45%20AM.jpeg" width="213.8" height="380" />

* Samsung Galaxy S8+   

<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/master/testing/media/WhatsApp%20Image%202018-03-09%20at%201.17.38%20AM.jpeg" width="213.8" height="430" />

* Samsung Galaxy S7

<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(29).png" width="213.8" height="380" />

As you can see the app adapts very well to any Android device no matter what the screen size. This is vital as we do not want to have the app looking different or distorted
on some devices. During the course of our interface we created a checklist of all the button and functionality and ticked off each item as "works as indended".

## 2 Unit Testing

During the course of our testing, we decided it was crucial to unit test methods/pieces of code that were run most often and contained any user generated input. These 
pressure points (frequently utilized pieces of data) would be the highest risk of something going wrong. As a result of this, we decided to unit test the following functions.

For further in-depth information on unit testing see [Android Studio Logs](#3-android-studio-logs) and [Unit Test Files](https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/tree/master/testing/unit_test_files)

### Testing Login and Register input

<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/master/testing/media/Screen%20Shot%202018-03-08%20at%2000.53.31.png" width="1280" height="800" />

We used Google Firebase for a lot of our email and password authentication but this test further implements how we set our restrictions within Firebase (double asserted so
to speak). These pieces of code are present in the login/register screen. The functions here have the below functionality.

* Password must contain at least 6 characters
* Email must be valid (contain an @ address)
* Neither field can be empty
* Leading and trailing whitespace is removed

<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/master/testing/media/Screen%20Shot%202018-03-08%20at%2001.00.29.png" width="1280" height="800" />

### Testing Barcode Retrieval

<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/master/testing/media/Screen%20Shot%202018-03-08%20at%2000.16.52.png" width="1280" height="800" />

This testing function ensures that the barcode we are scanning is of EAN-13 form. If the scanning of an incorrect barcode occurs the retrieval from the database
will be compromised and yield to some sort of crash in the app. This test prevents such an occurrence from happening. It is found in the scan barcode screen.
This function makes sure the barcode is exactly 13 __digits__ long.

### Testing User Profiles
<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/master/testing/media/Screen%20Shot%202018-03-08%20at%2000.02.38.png" width="1280" height="800" />

The save and add tests limits the number of errors a user can make while creating a profile. 

* Prevents duplicate ingredients
* Stops empty ingredients being added
* Converts item to all lowercase (for comparison purposes)
* Deletes whitespace

<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/master/testing/media/Screen%20Shot%202018-03-08%20at%2000.12.48.png" width="1280" height="800" />


### Tesco product retrival

<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/master/testing/media/Screen%20Shot%202018-03-08%20at%2000.38.32.png" width="1280" height="800" />

Tesco's database is extremely flawed. In order to use the ingredients of a product, we needed to create a workaround algorithm which is further outlined in Technical Manual. 
This particular algorithm:

* Gets rid of non-ingredient strings (eg. Contains, preservatives etc.)
* Only takes actual ingredient names 
* Removes duplicate ingredients
* Lowercases all ingredients for comparing purposes
* Removes whitespace and newline characters

### Comparison algorithm

<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/master/testing/media/Screen%20Shot%202018-03-08%20at%2000.22.13.png" width="1280" height="800" />

The comparison test ensures that the algorithm designed for comparing the ingredients of a product and the user's restrictions is working properly. If an element within a user's
restrictions is found in the product ingredients it is returned to the user as the reason (or one of many reasons) why the user cannot consume sed product. For obvious reasons, this 
is the most important function of our app. If it malfunctions users could be at serious risk so fully testing this particular method was vital.

## 3 Android Studio Logs

![](testing/media/logs.PNG)
![](testing/media/logtext.PNG)

Throughout the implementation of this project, we have used Logs to track the progress of our implementation progress of each function or piece of code. Because this is an 
application which needs to be tested on an external device or emulator it is impossible to track the progress of our code on an Android Device. Thus, we have learned to 
use logs provided to us in Android Studio. When we were compiling a program the logcat (shown in the first image above) would notify us of any error it has detected, here
is where you can find Java errors, as well as compile error in relation to XML layout files or build files. The log showed us where the problem occurred and what the problem was
highlighted in red. Also, logs were a great way to see the flow of data through ShoppingPal. Here when we implement the `Log.i("Message", barcodeNumber);` line within our
code the barcode number was outputted in the logs rather than outputting it to a device. This also came to great help when we had the product scanning and comparison feature
finished before the user interface and we needed to text product retrieval and comparison feature, the second image above shows the log output of 2 products. Here we printed:
* Selected profile
* Barcode number
* Product contents
* Matched contents
* Message (safe/unsafe)

This was the flow of data in our app from the moment a person select a profile to the result. The data flow for the first and second product can be seen highlighted red and
blue respectively in the console. This gave us a real-world test of products and their results.

## 4 User Testing

To user test, we set up a simple survey of 5 questions and asked 10 people to play around with the app for a few minutes and then complete the survey. Here are our results.
We ran the survey locally (in order for surveyees to use the app). Although all users were friends and family we tried to choose them from as broad a demographic as possible.
Surveyees had mixed technical backgrounds, were from all aspects of the age spectrum, different nationalities and did not necessarily suffer from food allergies.

![](testing/media/Screen Shot 2018-03-09 at 03.15.41.png)
![](testing/media/Screen Shot 2018-03-09 at 03.16.00.png)
![](testing/media/Screen Shot 2018-03-09 at 03.16.14.png)
![](testing/media/Screen Shot 2018-03-09 at 03.16.26.png)

Question 5 asked users what improvements could be made to the app. Our top response was to change the permission handling, something that was rectified and discussed below.
Other comments included making the app available for more stores as not everyone shops in Tesco. This was something we had hoped would be easier but is something we see 
happening in the future of our app. If given a longer timeframe to complete the project we would certainly be able to achieve this.

In the course of our user testing, we discovered that if a user wishes to scan a barcode he/she must enable permissions to do so within settings. A few of the surveyees found
this challenging and 2, in particular, couldn't achieve it at all. This made the app more or less unusable. We then decided to make changing permissions a pop up within the app
so that users don't have to look for it in settings. This was probably the best change we made to the app due to learning from testing.

We learned more from user testing than any other form of testing individually. Getting feedback from a different perspective was essential to the final few changes we made to the UI.
Link to our [survey](https://www.surveymonkey.com/results/SM-KQFX7J268/)

## 5 Heuristic Analysis

Our aim for this user interface was to make it as intuitive and easy to use as possible. This means making it accessible to users from every technical background and all ages.
To achieve this we did the following:
* Made buttons large and bright
* Had good contrast ratio throughout
* Used the same colour scheme
* Good spacing between buttons and links
* Menu hierarchy is ordered by popularity of actions (eg. scan button is centralized as it is the button a user will press the most, whereas the settings button is in the 
corner as it will not be used as often)

## 6 Shneiderman's Eight Golden Rules

### Strive for consistency

Throughout the app, the same theme is applied. From the minute the user enters the app, they are met with the blue and red colours of ShoppingPal. All buttons 
stand out due to good contrast ratios and simple colour schemes. Button placement is maintained, on the home screen, the user will be able to access their settings
by clicking on the cog in the top right-hand corner of their screen. A user need only press the back button on their device to reach the previous screen. This
uniform way of undoing one's actions is a key part of our UI design. Once a user has used the app for a short period of time icons should be familiar to them.
They will instantly recognize the menu layout and option hierarchy. We feel this consistency throughout the app will aid the user in achieving what they want 
to do as quickly and easily as possible.

The ShoppingPal logo can be seen from the moment a user decides to download to logging in. This consistency we feel is vital to gain the user's trust so that they
know they are getting the same experience throughout using the app. The icon also creates a familiar environment and something the user remember about the app as it is
our logo throughout this project.

### Enable frequent users to use shortcuts

Once a user has registered he/she is not required to log in they are automatically brought to their home screen. They will be met with this screen every time they
open the app provided they have not logged out. This prevents having to log in every time a user wishes to use the app which can be time-consuming and tedious. 
If a user successfully creates a profile it will be instantly saved in their profile selection drop down menu so that they can use this profile as often as they wish.
This will be extremely useful for users with obscure allergies or users who wish to remove a single ingredient from their diet. When a user presses the scan button
for the first time they will be asked for permission to use the rear camera, this is a once off pop-up so that frequent users don’t have to grant permissions every 
time they wish to scan a barcode. This way increased exposure to our UI will allow users to perform tasks faster. The profile that is selected will stay the same until
the user decides to change it, even when the app is closed and opened again, this means that a user does not need to select an item every time they launch the app.

<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(8).png" width="213.8" height="380" />

### Offer informative feedback

Throughout the app, all buttons, text views, edit texts, and drop down menus are labeled, and in some cases, icons are used such as a lock on the password edit text, and
the camera icon on the Scan button. When a user created a profile they will be informed that the profile has been created successfully and when a user adds an item
they will see it added to the list along with a Toast message. When a user scans a barcode of an item contained in the Tesco database, they will receive concise and 
accurate information about the product based on the profile they have selected. Contained within this feedback is:
* Whether or not the product is safe for consumption.
* Product name
* Barcode number
* Ingredients found in the product
* Ingredients specifically not safe for the user to consume (if any)
* Clear traffic light system used

<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(21).png" width="213.8" height="380" />
<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(20).png" width="213.8" height="380" />

### Design dialogs to yield closure

It is important that users have a clear path to their goal. For instance, if a user wants to create a profile and then scan a product using that profile this can be
done extremely easily. A user presses the create profile button after each ingredient is added individually to the profile they can see in real time the list is
updated. Upon saving that profile the user is given clear feedback that the profile has been created successfully. The user now only needs to select this profile from
the drop-down menu and press the scan button. Each action has led the user to their goal and they are given feedback at each step along the way. At no point is the
user left wondering if an action has been completed.

<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(5).png" width="213.8" height="380" />
<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(6).png" width="213.8" height="380" />
<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(7).png" width="213.8" height="380" />
 
### Offer simple error handling

When a user makes an error they will be met with a Toast message giving them information on what they are doing wrong. When registering, for example, a user must enter
a valid email address (there must be an @ contained). Likewise, a user’s password must be at least 6 characters long. If a user has less than this amount they will be
told that their password should contain at least 6 characters. This clear and concise error handling prevents confusion and leads to a better experience all round.

<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(15).png" width="213.8" height="380" />

### Permit easy reversal of actions

A systematic way a user can undo their actions is to press the back button on the device they are using. The integration of this simple ability cannot be underestimated
as it is the main way android users natively undo their actions. In extreme circumstances such as the deletion of one’s account a user is asked are they sure they would
like to do this? This prompt is important as it is not something a user will be doing often and can be pressed by mistake.

<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(3).png" width="213.8" height="380" />

### Support internal locus of control

It is important that a user feels that they are in control. A user has to be the initiator of each action rather than reacting to the UI. Our users should never ask 
themselves “How did I get to this screen?”. With each button, an action well labeled it is hard for an item to make a mistake in what they are trying to achieve.

### Reduce short-term memory load
All buttons are intuitively laid out. The methods which the user will use the most stand out more, are larger and are centralized rather than methods the user may only use
once or twice are out of the way in case of accidental activation. The UI is simple and easy to understand so that the learning curve is not steep. A user should be able to 
operate ShoppingPal seamlessly on their first use no matter what their previous knowledge of user interfaces is.